package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.services.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@ContextConfiguration("/test-services-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    private static String assertMsg;
    private UserDTO userDTO;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from UserServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        userDTO = new UserDTO("test", "test", String.valueOf(counter), "test", "test", 0d,
                UserStatus.OFLINE, new Date(), UserRole.CLIENT);
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        userDTO = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        UserDTO newUserDTO;
        id = userService.insert(userDTO);
        userDTO.setId(id);
        newUserDTO = userService.getById(userDTO.getId());
        Assert.assertEquals(msg, userDTO, newUserDTO);
    }

    @Test
    public void update() throws Exception {
        throw new Exception();
    }

    @Test
    public void deleteById() throws Exception {
        throw new Exception();
    }

    @Test
    public void getAll() throws Exception {
        throw new Exception();
    }

    @Test
    public void getByUsername() throws Exception {
        throw new Exception();
    }

    @Test
    public void getByUsernamePassword() throws Exception {
        throw new Exception();
    }

    @Test
    public void getByGap() throws Exception {
        throw new Exception();
    }
}