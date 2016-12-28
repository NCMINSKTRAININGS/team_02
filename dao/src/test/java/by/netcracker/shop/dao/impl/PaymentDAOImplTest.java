package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.PaymentDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Payment;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class PaymentDAOImplTest {
    private static String assertMsg;
    private static int counter;
    @Autowired
    private PaymentDAO paymentDAO;
    private Payment payment;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from PaymentDAOImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        payment = new Payment(String.valueOf(counter), "test");
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        payment = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Payment newPayment;
        id = paymentDAO.insert(payment);
        payment.setId(id);
        newPayment = paymentDAO.getById(payment.getId());
        Assert.assertEquals(msg, payment, newPayment);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Payment newPayment;
        id = paymentDAO.insert(payment);
        payment.setId(id);
        newPayment = paymentDAO.getById(payment.getId());
        Assert.assertEquals(msg, payment, newPayment);

        payment.setName("new_name");
        payment.setDescription("new_description");

        paymentDAO.update(payment);
        newPayment = paymentDAO.getById(id);
        Assert.assertEquals(msg, payment, newPayment);
        paymentDAO.deleteById(id);

        Throwable ex = null;
        try {
            paymentDAO.update(newPayment);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Payment newPayment;

        id = paymentDAO.insert(payment);
        payment.setId(id);
        newPayment = paymentDAO.getById(payment.getId());
        Assert.assertEquals(msg, payment, newPayment);
        paymentDAO.deleteById(id);
        newPayment = paymentDAO.getById(id);
        Assert.assertNull(msg, newPayment);

        Throwable ex = null;
        try {
            paymentDAO.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Payment> oldPayments;
        List<Payment> newPayments;
        Payment newPayment;
        Long id;

        oldPayments = paymentDAO.getAll();
        Assert.assertNotNull(msg, oldPayments);
        newPayment = new Payment(payment);
        newPayment.setName(String.valueOf(counter));
        counter += 1;
        id = paymentDAO.insert(payment);
        payment.setId(id);
        id = paymentDAO.insert(newPayment);
        newPayment.setId(id);

        newPayments = paymentDAO.getAll();
        Assert.assertEquals(msg, oldPayments.size() + 2, newPayments.size());
        Assert.assertTrue(msg, newPayments.contains(payment));
        Assert.assertTrue(msg, newPayments.contains(newPayment));

        for (Payment value : newPayments) {
            paymentDAO.deleteById(value.getId());
        }
        newPayments = paymentDAO.getAll();
        Assert.assertNotNull(msg, newPayments);
        Assert.assertTrue(msg, newPayments.size() == 0);
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = paymentDAO.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        paymentDAO.insert(payment);
        newCount = paymentDAO.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Payment> payments;
        Payment newPayment;
        Long id;

        payments = paymentDAO.getAll();
        Assert.assertNotNull(msg, payments);
        for (Payment value : payments) {
            paymentDAO.deleteById(value.getId());
        }
        payments = paymentDAO.getAll();
        Assert.assertNotNull(msg, payments);
        Assert.assertTrue(msg, payments.size() == 0);
        for (int i = 0; i < 10; i++) {
            newPayment = new Payment(payment);
            newPayment.setName(String.valueOf(counter));
            counter += 1;
            id = paymentDAO.insert(newPayment);
            newPayment.setId(id);
            payments.add(newPayment);
        }

        Assert.assertEquals(msg, paymentDAO.getByGap(0, 3), payments.subList(0, 0 + 3));
        Assert.assertEquals(msg, paymentDAO.getByGap(3, 5), payments.subList(3, 3 + 5));
        Assert.assertEquals(msg, paymentDAO.getByGap(2, 8), payments.subList(2, 2 + 8));
        Assert.assertEquals(msg, paymentDAO.getByGap(6, 7), payments.subList(6, payments.size()));
        Assert.assertEquals(msg, paymentDAO.getByGap(-4, 3), payments.subList(0, 0 + 3));
        Assert.assertEquals(msg, paymentDAO.getByGap(0, -1), payments.subList(0, payments.size()));
        Assert.assertEquals(msg, paymentDAO.getByGap(payments.size(), 1), payments.subList(0, 0));
    }
}