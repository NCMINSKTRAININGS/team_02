package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.DeliveryDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Delivery;
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
public class DeliveryDAOImplTest {
    private static String assertMsg;
    private static int counter;
    @Autowired
    private DeliveryDAO deliveryDAO;
    private Delivery delivery;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from DeliveryDAOImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        delivery = new Delivery(String.valueOf(counter), "test");
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        delivery = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Delivery newDelivery;
        id = deliveryDAO.insert(delivery);
        delivery.setId(id);
        newDelivery = deliveryDAO.getById(delivery.getId());
        Assert.assertEquals(msg, delivery, newDelivery);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Delivery newDelivery;
        id = deliveryDAO.insert(delivery);
        delivery.setId(id);
        newDelivery = deliveryDAO.getById(delivery.getId());
        Assert.assertEquals(msg, delivery, newDelivery);

        delivery.setName("new_name");
        delivery.setDescription("new_description");

        deliveryDAO.update(delivery);
        newDelivery = deliveryDAO.getById(id);
        Assert.assertEquals(msg, delivery, newDelivery);
        deliveryDAO.deleteById(id);

        Throwable ex = null;
        try {
            deliveryDAO.update(newDelivery);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Delivery newDelivery;

        id = deliveryDAO.insert(delivery);
        delivery.setId(id);
        newDelivery = deliveryDAO.getById(delivery.getId());
        Assert.assertEquals(msg, delivery, newDelivery);
        deliveryDAO.deleteById(id);
        newDelivery = deliveryDAO.getById(id);
        Assert.assertNull(msg, newDelivery);

        Throwable ex = null;
        try {
            deliveryDAO.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Delivery> oldDeliveries;
        List<Delivery> newDeliveries;
        Delivery newDelivery;
        Long id;

        oldDeliveries = deliveryDAO.getAll();
        Assert.assertNotNull(msg, oldDeliveries);
        newDelivery = new Delivery(delivery);
        newDelivery.setName(String.valueOf(counter));
        counter += 1;
        id = deliveryDAO.insert(delivery);
        delivery.setId(id);
        id = deliveryDAO.insert(newDelivery);
        newDelivery.setId(id);

        newDeliveries = deliveryDAO.getAll();
        Assert.assertEquals(msg, oldDeliveries.size() + 2, newDeliveries.size());
        Assert.assertTrue(msg, newDeliveries.contains(delivery));
        Assert.assertTrue(msg, newDeliveries.contains(newDelivery));

        for (Delivery value : newDeliveries) {
            deliveryDAO.deleteById(value.getId());
        }
        newDeliveries = deliveryDAO.getAll();
        Assert.assertNotNull(msg, newDeliveries);
        Assert.assertTrue(msg, newDeliveries.size() == 0);
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = deliveryDAO.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        deliveryDAO.insert(delivery);
        newCount = deliveryDAO.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Delivery> deliveries;
        Delivery newDelivery;
        Long id;

        deliveries = deliveryDAO.getAll();
        Assert.assertNotNull(msg, deliveries);
        for (Delivery value : deliveries) {
            deliveryDAO.deleteById(value.getId());
        }
        deliveries = deliveryDAO.getAll();
        Assert.assertNotNull(msg, deliveries);
        Assert.assertTrue(msg, deliveries.size() == 0);
        for (int i = 0; i < 10; i++) {
            newDelivery = new Delivery(delivery);
            newDelivery.setName(String.valueOf(counter));
            counter += 1;
            id = deliveryDAO.insert(newDelivery);
            newDelivery.setId(id);
            deliveries.add(newDelivery);
        }

        Assert.assertEquals(msg, deliveryDAO.getByGap(0, 3), deliveries.subList(0, 0 + 3));
        Assert.assertEquals(msg, deliveryDAO.getByGap(3, 5), deliveries.subList(3, 3 + 5));
        Assert.assertEquals(msg, deliveryDAO.getByGap(2, 8), deliveries.subList(2, 2 + 8));
        Assert.assertEquals(msg, deliveryDAO.getByGap(6, 7), deliveries.subList(6, deliveries.size()));
        Assert.assertEquals(msg, deliveryDAO.getByGap(-4, 3), deliveries.subList(0, 0 + 3));
        Assert.assertEquals(msg, deliveryDAO.getByGap(0, -1), deliveries.subList(0, deliveries.size()));
        Assert.assertEquals(msg, deliveryDAO.getByGap(deliveries.size(), 1), deliveries.subList(0, 0));
    }
}