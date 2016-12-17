package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.pojo.User;
import org.junit.*;
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
        id = userDAO.insert(user);
        user.setId(id);
        User newUser = userDAO.getById(user.getId());
        Assert.assertTrue(newUser != null);
        Assert.assertEquals(user, newUser);
    }
}