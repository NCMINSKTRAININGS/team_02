package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.PaymentService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("/test-services-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class PaymentServiceImplTest {
    @Autowired
    private PaymentService paymentService;

    private static String assertMsg;
    private PaymentDTO payment;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from PaymentServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        payment = new PaymentDTO(null, String.valueOf(counter), "test");
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
        PaymentDTO newPaymentDTO;
        id = paymentService.insert(payment);
        payment.setId(id);
        newPaymentDTO = paymentService.getById(payment.getId());
        Assert.assertEquals(msg, payment, newPaymentDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        PaymentDTO newPaymentDTO;
        id = paymentService.insert(payment);
        payment.setId(id);
        newPaymentDTO = paymentService.getById(payment.getId());
        Assert.assertEquals(msg, payment, newPaymentDTO);

        payment.setDescription("new_description");

        paymentService.update(payment);
        newPaymentDTO = paymentService.getById(id);
        Assert.assertEquals(msg, payment, newPaymentDTO);
        paymentService.deleteById(id);
        PaymentDTO newPayment = paymentService.getById(id);
        Assert.assertNull(msg, newPayment);

        Throwable ex = null;
        try {
            paymentService.update(newPaymentDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        PaymentDTO newPayment;

        id = paymentService.insert(payment);
        payment.setId(id);
        newPayment = paymentService.getById(payment.getId());
        Assert.assertEquals(msg, payment, newPayment);
        paymentService.deleteById(id);
        newPayment = paymentService.getById(id);
        Assert.assertNull(msg, newPayment);

        Throwable ex = null;
        try {
            paymentService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<PaymentDTO> oldPayments;
        List<PaymentDTO> newPayments;
        PaymentDTO newPayment;
        Long id;

        oldPayments = paymentService.getAll();
        Assert.assertNotNull(msg, oldPayments);
        newPayment = new PaymentDTO(payment);
        newPayment.setName(String.valueOf(counter));
        counter += 1;
        id = paymentService.insert(payment);
        payment.setId(id);
        id = paymentService.insert(newPayment);
        newPayment.setId(id);

        newPayments = paymentService.getAll();
        Assert.assertEquals(msg, oldPayments.size() + 2, newPayments.size());
        Assert.assertTrue(msg, newPayments.contains(payment));
        Assert.assertTrue(msg, newPayments.contains(newPayment));

        for (PaymentDTO value : newPayments) {
            paymentService.deleteById(value.getId());
        }
        newPayments = paymentService.getAll();
        Assert.assertNotNull(msg, newPayments);
        Assert.assertTrue(msg, newPayments.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<PaymentDTO> payments;
        PaymentDTO newPayment;
        Long id;

        payments = paymentService.getAll();
        Assert.assertNotNull(msg, payments);
        for (PaymentDTO value : payments) {
            paymentService.deleteById(value.getId());
        }
        payments = paymentService.getAll();
        Assert.assertNotNull(msg, payments);
        Assert.assertTrue(msg, payments.size() == 0);
        for (int i = 0; i < 10; i++) {
            newPayment = new PaymentDTO(payment);
            newPayment.setName(String.valueOf(counter));
            counter += 1;
            id = paymentService.insert(newPayment);
            newPayment.setId(id);
            payments.add(newPayment);
        }

        Assert.assertEquals(msg, paymentService.getByGap(0, 3), payments.subList(0, 0 + 3));
        Assert.assertEquals(msg, paymentService.getByGap(3, 5), payments.subList(3, 3 + 5));
        Assert.assertEquals(msg, paymentService.getByGap(2, 8), payments.subList(2, 2 + 8));
        Assert.assertEquals(msg, paymentService.getByGap(6, 7), payments.subList(6, payments.size()));
        Assert.assertEquals(msg, paymentService.getByGap(-4, 3), payments.subList(0, 0 + 3));
        Assert.assertEquals(msg, paymentService.getByGap(0, -1), payments.subList(0, payments.size()));
        Assert.assertEquals(msg, paymentService.getByGap(payments.size(), 1), payments.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = paymentService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        paymentService.insert(payment);
        newCount = paymentService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}