package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.User;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        Assert.assertEquals(user, newUser);
    }

    @Test
    public void update() throws Exception {
        Long id;
        User newUser;
        boolean updated;
        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals(user, newUser);

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
        Assert.assertTrue(updated);
        newUser = userDAO.getById(id);
        Assert.assertEquals(user, newUser);
        Assert.assertTrue(userDAO.deleteById(id));

        Throwable ex = null;
        try {
            userDAO.update(newUser);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        Long id;
        boolean deleted;
        User newUser;
        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals(user, newUser);
        deleted = userDAO.deleteById(id);
        Assert.assertTrue(deleted);
        newUser = userDAO.getById(id);
        Assert.assertNull(newUser);
        deleted = userDAO.deleteById(id);
        Assert.assertFalse(deleted);
    }

    @Test
    public void getAll() throws Exception {
        //todo
        throw new Exception();
    }
}