package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.Delivery;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.Payment;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class OrderDAOImplTest {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DeliveryDAO deliveryDAO;
    @Autowired
    private PaymentDAO paymentDAO;

    private Order order;
    private Delivery delivery;
    private Payment payment;

    @Before
    public void setUp(){

    }
}
