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
public class OrderDaoImplTest {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DeliveryDao deliveryDao;
    @Autowired
    private PaymentDao paymentDao;

    private Order order;
    private Delivery delivery;
    private Payment payment;

    @Before
    public void setUp(){

    }
}
