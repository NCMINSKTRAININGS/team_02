package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@ContextConfiguration("/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class UserDAOImplTest {
    @Autowired
    private UserDAO userDAO;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("test", "test", "test", "test", "test", "test", 0,
                UserStatus.OFLINE, new Date(), UserRole.CLIENT);
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void insert() throws Exception {
        Long id;
        User newUser;
        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals("insert() with getId() methods failed: ", user, newUser);
    }

    @Test
    public void update() throws Exception {
        Long id;
        User newUser;
        boolean updated;
        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals("insert() with getById() methods failed: ", user, newUser);

        user.setFirstName("new_firstName");
        user.setLastName("new_lastName");
        user.setUsername("new_username");
        user.setEmail("new@email.com");
        user.setRole(UserRole.ADMIN);
        user.setDiscount(5);
        user.setBirthday(new Date());
        user.setPassword("new_password");
        user.setSalt("new_salt");
        user.setStatus(UserStatus.ONLINE);

        updated = userDAO.update(user);
        Assert.assertTrue("update() method failed: ", updated);
        newUser = userDAO.getById(id);
        Assert.assertEquals("update() with getById() methods failed: ", user, newUser);
        Assert.assertTrue("deleteById() method failed: ", userDAO.deleteById(id));

        Throwable ex = null;
        try {
            userDAO.update(newUser);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue("update() method failed: ", ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        Long id;
        boolean deleted;
        User newUser;

        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals("insert() with getById() methods failed: ", user, newUser);
        deleted = userDAO.deleteById(id);
        Assert.assertTrue("deleteById() method failed: ", deleted);
        newUser = userDAO.getById(id);
        Assert.assertNull("getById() method failed: ", newUser);
        deleted = userDAO.deleteById(id);
        Assert.assertFalse("deleteById() method failed: ", deleted);
    }

    @Test
    public void getAll() throws Exception {
        List<User> oldUsers;
        List<User> newUsers;
        User newUser;
        Long id;
        boolean deleted;

        oldUsers = userDAO.getAll();
        Assert.assertNotNull("getAll() method failed: ", oldUsers);
        newUser = new User(user);
        id = userDAO.insert(user);
        user.setId(id);
        id = userDAO.insert(newUser);
        newUser.setId(id);

        newUsers = userDAO.getAll();
        Assert.assertEquals("insert() with getAll() methods failed: ", oldUsers.size() + 2, newUsers.size());
        Assert.assertTrue("insert() with getAll() methods failed: ", newUsers.contains(user));
        Assert.assertTrue("insert() with getAll() methods failed: ", newUsers.contains(newUser));

        for (User value : newUsers) {
            deleted = userDAO.deleteById(value.getId());
            Assert.assertTrue("deleteById() method failed: ", deleted);
        }
        newUsers = userDAO.getAll();
        Assert.assertNotNull("getAll() method failed: ", newUsers);
        Assert.assertTrue("getAll() method failed: ", newUsers.size() == 0);
    }
}
