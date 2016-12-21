package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.*;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.pojo.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration("/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PaymentDAO paymentDAO;
    @Autowired
    private DeliveryDAO deliveryDAO;
    @Autowired
    private ProductDAO productDAO;


    private Category category;
    private Manufacturer manufacturer;
    private User user;
    private Product product;
    private Payment payment;
    private Delivery delivery;
    private Order order;
    private List<Product> products = new ArrayList<>();
    private List<Order> expectedOrders = new ArrayList<>();
    private Object[] expectedGroupedOrder = new Object[3];


    @Before
    public void setUp() throws Exception {
        user = new User("test", "test", "test", "test", "test", "test", 0,
                UserStatus.OFLINE, new Date(), UserRole.CLIENT);
        userDAO.insert(user);

        product = new Product(category, manufacturer, "test", "test", 1, "test", 1);
        productDAO.insert(product);
        products.add(0, product);

        payment = new Payment("test", "test");
        paymentDAO.insert(payment);

        delivery = new Delivery("test", "test");
        deliveryDAO.insert(delivery);

        order = new Order(user, payment, delivery, "test", 0, products);
        orderDao.insert(order);
        expectedOrders.add(0, order);

        expectedGroupedOrder[0] = 1;
        expectedGroupedOrder[1] = "test";
        expectedGroupedOrder[2] = BigInteger.valueOf(1);

    }

    @After
    public void tearDown() throws Exception {
        delivery = null;
        order = null;
        payment = null;
        product = null;
        products = null;
        user = null;
    }

    @Test
    public void testGetOrdersByUser() throws Exception {
        List<Order> actualOrders = orderDao.getOrdersByUser(user);
        Assert.assertEquals(expectedOrders, actualOrders);
    }

    @Ignore
    @Test
    public void testGetGroupedOrders() throws Exception {
        List<Object[]> actualList = orderDao.getGroupedOrders();
        Assert.assertEquals(expectedGroupedOrder, actualList.get(actualList.size() - 1));
    }

    @Test
    public void testInsert() throws Exception {
        orderDao.insert(order);
        Order actualOrder = orderDao.getById(order.getId());
        Assert.assertEquals(expectedOrders.get(expectedOrders.size() - 1), actualOrder);
    }

    @Test
    public void testGetById() throws Exception {
        Order actualOrder = orderDao.getById(order.getId());
        Assert.assertEquals(expectedOrders.get(expectedOrders.size() - 1), actualOrder);
    }

    @Test
    public void testUpdate() throws Exception {
        Order order = this.order;
        order.setUser(user);
        order.setPayment(payment);
        order.setComment("comment");
        order.setDelivery(delivery);
        order.setProducts(products);
        order.setPrice(90);
        orderDao.update(order);
        Assert.assertEquals(this.order, order);

    }

    @Test
    public void testDeleteById() throws Exception {
        int ordersBefore = orderDao.getAll().size();
        orderDao.deleteById(order.getId());
        int ordersAfter = orderDao.getAll().size();
        Assert.assertNotEquals(ordersBefore, ordersAfter);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Order> orders = orderDao.getAll();
        Assert.assertNotNull(orders);
    }

    @Test
    public void testGetCount() throws Exception {
        Long count = orderDao.getCount();
        Long getAll = Long.valueOf(orderDao.getAll().size());
        Assert.assertEquals(count, getAll);
    }

    @Test
    public void testGetByGap() throws Exception {
        List<Order> orders = orderDao.getByGap(0, 2);
        Assert.assertTrue(orders.size() <= 2);

    }
}