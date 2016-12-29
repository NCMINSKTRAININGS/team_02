package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.DeliveryDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.DeliveryService;
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
public class DeliveryServiceImplTest {
    @Autowired
    private DeliveryService deliveryService;

    private static String assertMsg;
    private DeliveryDTO delivery;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from DeliveryServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        delivery = new DeliveryDTO(null, String.valueOf(counter), "test");
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
        DeliveryDTO newDeliveryDTO;
        id = deliveryService.insert(delivery);
        delivery.setId(id);
        newDeliveryDTO = deliveryService.getById(delivery.getId());
        Assert.assertEquals(msg, delivery, newDeliveryDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        DeliveryDTO newDeliveryDTO;
        id = deliveryService.insert(delivery);
        delivery.setId(id);
        newDeliveryDTO = deliveryService.getById(delivery.getId());
        Assert.assertEquals(msg, delivery, newDeliveryDTO);

        delivery.setDescription("new_description");

        deliveryService.update(delivery);
        newDeliveryDTO = deliveryService.getById(id);
        Assert.assertEquals(msg, delivery, newDeliveryDTO);
        deliveryService.deleteById(id);
        DeliveryDTO newDelivery = deliveryService.getById(id);
        Assert.assertNull(msg, newDelivery);

        Throwable ex = null;
        try {
            deliveryService.update(newDeliveryDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        DeliveryDTO newDelivery;

        id = deliveryService.insert(delivery);
        delivery.setId(id);
        newDelivery = deliveryService.getById(delivery.getId());
        Assert.assertEquals(msg, delivery, newDelivery);
        deliveryService.deleteById(id);
        newDelivery = deliveryService.getById(id);
        Assert.assertNull(msg, newDelivery);

        Throwable ex = null;
        try {
            deliveryService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<DeliveryDTO> oldDeliveries;
        List<DeliveryDTO> newDeliveries;
        DeliveryDTO newDelivery;
        Long id;

        oldDeliveries = deliveryService.getAll();
        Assert.assertNotNull(msg, oldDeliveries);
        newDelivery = new DeliveryDTO(delivery);
        newDelivery.setName(String.valueOf(counter));
        counter += 1;
        id = deliveryService.insert(delivery);
        delivery.setId(id);
        id = deliveryService.insert(newDelivery);
        newDelivery.setId(id);

        newDeliveries = deliveryService.getAll();
        Assert.assertEquals(msg, oldDeliveries.size() + 2, newDeliveries.size());
        Assert.assertTrue(msg, newDeliveries.contains(delivery));
        Assert.assertTrue(msg, newDeliveries.contains(newDelivery));

        for (DeliveryDTO value : newDeliveries) {
            deliveryService.deleteById(value.getId());
        }
        newDeliveries = deliveryService.getAll();
        Assert.assertNotNull(msg, newDeliveries);
        Assert.assertTrue(msg, newDeliveries.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<DeliveryDTO> deliveries;
        DeliveryDTO newDelivery;
        Long id;

        deliveries = deliveryService.getAll();
        Assert.assertNotNull(msg, deliveries);
        for (DeliveryDTO value : deliveries) {
            deliveryService.deleteById(value.getId());
        }
        deliveries = deliveryService.getAll();
        Assert.assertNotNull(msg, deliveries);
        Assert.assertTrue(msg, deliveries.size() == 0);
        for (int i = 0; i < 10; i++) {
            newDelivery = new DeliveryDTO(delivery);
            newDelivery.setName(String.valueOf(counter));
            counter += 1;
            id = deliveryService.insert(newDelivery);
            newDelivery.setId(id);
            deliveries.add(newDelivery);
        }

        Assert.assertEquals(msg, deliveryService.getByGap(0, 3), deliveries.subList(0, 0 + 3));
        Assert.assertEquals(msg, deliveryService.getByGap(3, 5), deliveries.subList(3, 3 + 5));
        Assert.assertEquals(msg, deliveryService.getByGap(2, 8), deliveries.subList(2, 2 + 8));
        Assert.assertEquals(msg, deliveryService.getByGap(6, 7), deliveries.subList(6, deliveries.size()));
        Assert.assertEquals(msg, deliveryService.getByGap(-4, 3), deliveries.subList(0, 0 + 3));
        Assert.assertEquals(msg, deliveryService.getByGap(0, -1), deliveries.subList(0, deliveries.size()));
        Assert.assertEquals(msg, deliveryService.getByGap(deliveries.size(), 1), deliveries.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = deliveryService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        deliveryService.insert(delivery);
        newCount = deliveryService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}