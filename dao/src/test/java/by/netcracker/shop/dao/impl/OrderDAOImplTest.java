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

import java.util.*;

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
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ManufacturerDAO manufacturerDAO;


    private Category category;
    private Manufacturer manufacturer;
    private User user;
    private Product product;
    private Payment payment;
    private Delivery delivery;
    private Order order;
    private Set<Product> products = new HashSet<>();
    private List<Order> expectedOrders = new ArrayList<>();
    private Object[] expectedGroupedOrder = new Object[3];


    @Before
    public void setUp() throws Exception {
       user = new User("test", "test", "test", "test", "test", new Double(0),
                UserStatus.OFLINE, new Date(), UserRole.CLIENT, null);
        userDAO.insert(user);


        payment = new Payment("test", "test");
        paymentDAO.insert(payment);

        delivery = new Delivery("test", "test");
        deliveryDAO.insert(delivery);

        order = new Order(user, payment, delivery, "test", 0d, false);
        orderDao.insert(order);


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

    @Ignore
    @Test
    public void testGetOrdersByUser() throws Exception {
        List<Order> actualOrders = orderDao.getOrdersByUser(user);
        System.out.println(actualOrders.toString());
        Assert.assertEquals(expectedOrders, actualOrders);
    }

    @Ignore
    @Test
    public void testGetGroupedOrders() throws Exception {
        List<Object[]> actualList = orderDao.getGroupedOrders();
        Assert.assertEquals(expectedGroupedOrder, actualList.get(actualList.size() - 1));
    }

    @Ignore
    @Test
    public void testInsert() throws Exception {
        orderDao.insert(order);
        Order actualOrder = orderDao.getById(order.getId());
        Assert.assertEquals(expectedOrders.get(expectedOrders.size() - 1), actualOrder);
    }

    @Ignore
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
      //  order.setProducts(products);
        order.setPrice(90d);
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

    @Test
    public void getActiveOrderByUser()  throws Exception{
        List<Order> actualOrders = orderDao.getActiveOrderByUser(userDAO.getByUsername("12345"));
        System.out.println(actualOrders.toString());
    }
}