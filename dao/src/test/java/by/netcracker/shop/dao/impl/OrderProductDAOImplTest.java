package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.*;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.enums.UserStatus;
import by.netcracker.shop.pojo.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
public class OrderProductDAOImplTest {

    @Autowired
    private OrderProductDAO orderProductDAO;
    @Autowired
    private OrderDAO orderDAO;
    private Order order;
    @Autowired
    private ProductDAO productDAO;
    private Product product;
    @Autowired
    private UserDAO userDAO;
    private User user;
    @Autowired
    private DeliveryDAO deliveryDAO;
    private Delivery delivery;
    @Autowired
    private PaymentDAO paymentDAO;
    private Payment payment;
    private OrderProduct orderProduct;

    @Before
    public void setUp() throws Exception {
        user = new User("test","test","test","test","test",0.0, UserStatus.ONLINE, new Date(), UserRole.CLIENT, null);
        userDAO.insert(user);
        payment= new Payment("test","test");
        paymentDAO.insert(payment);
        delivery = new Delivery("test","test");
        deliveryDAO.insert(delivery);
        product = new Product(null, null, "test", "test", 0.0, "test", 0);
        productDAO.insert(product);
        order = new Order(user,payment,delivery,"test",0.0,false);
        orderDAO.insert(order);

        orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setPrice(0.0);
        orderProduct.setPruductQuantity(0);

    }

    @After
    public void tearDown() throws Exception {
        user=null;
        payment=null;
        delivery=null;
        product=null;
        order=null;
        orderProduct=null;
    }

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testDeleteById() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

        List<OrderProduct> orderProducts=orderProductDAO.getAll();
        for (OrderProduct orderProduct:orderProducts) {
            System.out.println(orderProduct.getOrder().toString()+"\n"
            +orderProduct.getProduct().toString()+"\n"+
            orderProduct.getPrice()+" "+orderProduct.getPruductQuantity());
        }
    }

    @Test
    public void testGetByUserId() throws Exception{
        List<OrderProduct> orderProducts=orderProductDAO.getByUserId(5l);
        for (OrderProduct orderProduct:orderProducts) {
            System.out.println(orderProduct.getOrder().toString()+"\n"
                    +orderProduct.getProduct().toString()+"\n"+
                    orderProduct.getPrice()+" "+orderProduct.getPruductQuantity());
        }
    }

    @Test
    public void testGetCount() throws Exception {

    }

    @Test
    public void testGetByGap() throws Exception {

    }
}