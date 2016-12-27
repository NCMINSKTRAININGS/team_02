package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@ContextConfiguration("/test-services-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    private static String assertMsg;
    private UserDTO user;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from UserServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        user = new UserDTO(null, "test", "test", String.valueOf(counter), "test", "test", 0d,
                UserStatus.OFLINE, new Date(), UserRole.CLIENT);
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        UserDTO newUserDTO;
        id = userService.insert(user);
        user.setId(id);
        newUserDTO = userService.getById(user.getId());
        Assert.assertEquals(msg, user, newUserDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        UserDTO newUserDTO;
        id = userService.insert(user);
        user.setId(id);
        newUserDTO = userService.getById(user.getId());
        Assert.assertEquals(msg, user, newUserDTO);

        user.setFirstName("new_firstName");
        user.setLastName("new_lastName");
        user.setUsername("new_username");
        user.setEmail("new@email.com");
        user.setRole(UserRole.ADMIN);
        user.setDiscount(5d);
        user.setBirthday(new Date());
        user.setPassword("new_password");
        user.setStatus(UserStatus.ONLINE);

        userService.update(user);
        newUserDTO = userService.getById(id);
        Assert.assertEquals(msg, user, newUserDTO);
        userService.deleteById(id);
        UserDTO newUser = userService.getById(id);
        Assert.assertNull(msg, newUser);

        Throwable ex = null;
        try {
            userService.update(newUserDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        UserDTO newUser;

        id = userService.insert(user);
        user.setId(id);
        newUser = userService.getById(user.getId());
        Assert.assertEquals(msg, user, newUser);
        userService.deleteById(id);
        newUser = userService.getById(id);
        Assert.assertNull(msg, newUser);

        Throwable ex = null;
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<UserDTO> oldUsers;
        List<UserDTO> newUsers;
        UserDTO newUser;
        Long id;

        oldUsers = userService.getAll();
        Assert.assertNotNull(msg, oldUsers);
        newUser = new UserDTO(user);
        newUser.setUsername(String.valueOf(counter));
        counter += 1;
        id = userService.insert(user);
        user.setId(id);
        id = userService.insert(newUser);
        newUser.setId(id);

        newUsers = userService.getAll();
        Assert.assertEquals(msg, oldUsers.size() + 2, newUsers.size());
        Assert.assertTrue(msg, newUsers.contains(user));
        Assert.assertTrue(msg, newUsers.contains(newUser));

        for (UserDTO value : newUsers) {
            userService.deleteById(value.getId());
        }
        newUsers = userService.getAll();
        Assert.assertNotNull(msg, newUsers);
        Assert.assertTrue(msg, newUsers.size() == 0);
    }

    @Test
    public void getByUsername() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        UserDTO newUser;
        Long id;

        id = userService.insert(user);
        user.setId(id);
        newUser = userService.getByUsername(user.getUsername());

        Assert.assertEquals(msg, user, newUser);
    }

    @Test
    public void getByUsernamePassword() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        UserDTO newUser;
        Long id;

        id = userService.insert(user);
        user.setId(id);
        newUser = userService.getByUsernamePassword(user.getUsername(), user.getPassword());

        Assert.assertEquals(msg, user, newUser);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<UserDTO> users;
        UserDTO newUser;
        Long id;

        users = userService.getAll();
        Assert.assertNotNull(msg, users);
        for (UserDTO value : users) {
            userService.deleteById(value.getId());
        }
        users = userService.getAll();
        Assert.assertNotNull(msg, users);
        Assert.assertTrue(msg, users.size() == 0);
        for (int i = 0; i < 10; i++) {
            newUser = new UserDTO(user);
            newUser.setUsername(String.valueOf(counter));
            counter += 1;
            id = userService.insert(newUser);
            newUser.setId(id);
            users.add(newUser);
        }

        Assert.assertEquals(msg, userService.getByGap(0, 3), users.subList(0, 0 + 3));
        Assert.assertEquals(msg, userService.getByGap(3, 5), users.subList(3, 3 + 5));
        Assert.assertEquals(msg, userService.getByGap(2, 8), users.subList(2, 2 + 8));
        Assert.assertEquals(msg, userService.getByGap(6, 7), users.subList(6, users.size()));
        Assert.assertEquals(msg, userService.getByGap(-4, 3), users.subList(0, 0 + 3));
        Assert.assertEquals(msg, userService.getByGap(0, -1), users.subList(0, users.size()));
        Assert.assertEquals(msg, userService.getByGap(users.size(), 1), users.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = userService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        userService.insert(user);
        newCount = userService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}