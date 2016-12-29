package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
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
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserDAO userDAO;

    private static String assertMsg;
    private static int counter;
    private OrderDTO order;
    private User userPOJO;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from OrderServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        userPOJO = new User("test", "test", String.valueOf(counter), "test", "test", 0d,
                UserStatus.OFLINE, new Date(), UserRole.CLIENT, null);
        userDAO.insert(userPOJO);
        order = new OrderDTO(null, "test", 0d, userPOJO, null, null, null);
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        order = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        OrderDTO newOrderDTO;
        id = orderService.insert(order);
        order.setId(id);
        newOrderDTO = orderService.getById(order.getId());
        Assert.assertEquals(msg, order, newOrderDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        OrderDTO newOrderDTO;
        id = orderService.insert(order);
        order.setId(id);
        newOrderDTO = orderService.getById(order.getId());
        Assert.assertEquals(msg, order, newOrderDTO);

        order.setComment("new_comment");
        order.setPrice(1d);

        orderService.update(order);
        newOrderDTO = orderService.getById(id);
        Assert.assertEquals(msg, order, newOrderDTO);
        orderService.deleteById(id);
        OrderDTO newOrder = orderService.getById(id);
        Assert.assertNull(msg, newOrder);

        Throwable ex = null;
        try {
            orderService.update(newOrderDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        OrderDTO newOrder;

        id = orderService.insert(order);
        order.setId(id);
        newOrder = orderService.getById(order.getId());
        Assert.assertEquals(msg, order, newOrder);
        orderService.deleteById(id);
        newOrder = orderService.getById(id);
        Assert.assertNull(msg, newOrder);

        Throwable ex = null;
        try {
            orderService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<OrderDTO> oldOrders;
        List<OrderDTO> newOrders;
        OrderDTO newOrder;
        Long id;

        oldOrders = orderService.getAll();
        Assert.assertNotNull(msg, oldOrders);
        newOrder = new OrderDTO(order);
        id = orderService.insert(order);
        order.setId(id);
        id = orderService.insert(newOrder);
        newOrder.setId(id);

        newOrders = orderService.getAll();
        Assert.assertEquals(msg, oldOrders.size() + 2, newOrders.size());
        Assert.assertTrue(msg, newOrders.contains(order));
        Assert.assertTrue(msg, newOrders.contains(newOrder));

        for (OrderDTO value : newOrders) {
            orderService.deleteById(value.getId());
        }
        newOrders = orderService.getAll();
        Assert.assertNotNull(msg, newOrders);
        Assert.assertTrue(msg, newOrders.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<OrderDTO> orders;
        OrderDTO newOrder;
        Long id;

        orders = orderService.getAll();
        Assert.assertNotNull(msg, orders);
        for (OrderDTO value : orders) {
            orderService.deleteById(value.getId());
        }
        orders = orderService.getAll();
        Assert.assertNotNull(msg, orders);
        Assert.assertTrue(msg, orders.size() == 0);
        for (int i = 0; i < 10; i++) {
            newOrder = new OrderDTO(order);
            id = orderService.insert(newOrder);
            newOrder.setId(id);
            orders.add(newOrder);
        }

        Assert.assertEquals(msg, orderService.getByGap(0, 3), orders.subList(0, 0 + 3));
        Assert.assertEquals(msg, orderService.getByGap(3, 5), orders.subList(3, 3 + 5));
        Assert.assertEquals(msg, orderService.getByGap(2, 8), orders.subList(2, 2 + 8));
        Assert.assertEquals(msg, orderService.getByGap(6, 7), orders.subList(6, orders.size()));
        Assert.assertEquals(msg, orderService.getByGap(-4, 3), orders.subList(0, 0 + 3));
        Assert.assertEquals(msg, orderService.getByGap(0, -1), orders.subList(0, orders.size()));
        Assert.assertEquals(msg, orderService.getByGap(orders.size(), 1), orders.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = orderService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        orderService.insert(order);
        newCount = orderService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Ignore
    @Test
    public void getOrdersByUser() throws Exception {
    //todo
    }

    @Ignore
    @Test
    public void getGroupedOrders() throws Exception {
    //todo
    }

    @Ignore
    @Test
    public void addProdToOrder() throws Exception {
    //todo
    }
}