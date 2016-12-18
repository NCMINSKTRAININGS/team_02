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

    private static String assertMsg;
    private User user;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from UserDAOImlpTest failed: ";
    }

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
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        User newUser;
        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals(msg, user, newUser);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        User newUser;
        boolean updated;
        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals(msg, user, newUser);

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
        Assert.assertTrue(msg, updated);
        newUser = userDAO.getById(id);
        Assert.assertEquals(msg, user, newUser);
        Assert.assertTrue(msg, userDAO.deleteById(id));

        Throwable ex = null;
        try {
            userDAO.update(newUser);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        boolean deleted;
        User newUser;

        id = userDAO.insert(user);
        user.setId(id);
        newUser = userDAO.getById(user.getId());
        Assert.assertEquals(msg, user, newUser);
        deleted = userDAO.deleteById(id);
        Assert.assertTrue(msg, deleted);
        newUser = userDAO.getById(id);
        Assert.assertNull(msg, newUser);
        deleted = userDAO.deleteById(id);
        Assert.assertFalse(msg, deleted);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<User> oldUsers;
        List<User> newUsers;
        User newUser;
        Long id;
        boolean deleted;

        oldUsers = userDAO.getAll();
        Assert.assertNotNull(msg, oldUsers);
        newUser = new User(user);
        id = userDAO.insert(user);
        user.setId(id);
        id = userDAO.insert(newUser);
        newUser.setId(id);

        newUsers = userDAO.getAll();
        Assert.assertEquals(msg, oldUsers.size() + 2, newUsers.size());
        Assert.assertTrue(msg, newUsers.contains(user));
        Assert.assertTrue(msg, newUsers.contains(newUser));

        for (User value : newUsers) {
            deleted = userDAO.deleteById(value.getId());
            Assert.assertTrue(msg, deleted);
        }
        newUsers = userDAO.getAll();
        Assert.assertNotNull(msg, newUsers);
        Assert.assertTrue(msg, newUsers.size() == 0);
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = userDAO.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        userDAO.insert(user);
        newCount = userDAO.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<User> users;
        User newUser;
        Long id;
        boolean deleted;

        users = userDAO.getAll();
        Assert.assertNotNull(msg, users);
        for (User value : users) {
            deleted = userDAO.deleteById(value.getId());
            Assert.assertTrue(msg, deleted);
        }
        users = userDAO.getAll();
        Assert.assertNotNull(msg, users);
        Assert.assertTrue(msg, users.size() == 0);
        for (int i = 0; i < 10; i++) {
            newUser = new User(user);
            id = userDAO.insert(newUser);
            newUser.setId(id);
            users.add(newUser);
        }

        Assert.assertEquals(msg, userDAO.getByGap(0, 3), users.subList(0, 0 + 3));
        Assert.assertEquals(msg, userDAO.getByGap(3, 5), users.subList(3, 3 + 5));
        Assert.assertEquals(msg, userDAO.getByGap(2, 8), users.subList(2, 2 + 8));
        Assert.assertEquals(msg, userDAO.getByGap(6, 7), users.subList(6, users.size()));
    }
}
