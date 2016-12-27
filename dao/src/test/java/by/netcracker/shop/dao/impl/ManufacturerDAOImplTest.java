package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Manufacturer;
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
public class ManufacturerDAOImplTest {
    private static String assertMsg;
    private static int counter;
    @Autowired
    private ManufacturerDAO manufacturerDAO;
    private Manufacturer manufacturer;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from ManufacturerDAOImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        manufacturer = new Manufacturer(String.valueOf(counter), "test", "test");
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        manufacturer = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Manufacturer newManufacturer;
        id = manufacturerDAO.insert(manufacturer);
        manufacturer.setId(id);
        newManufacturer = manufacturerDAO.getById(manufacturer.getId());
        Assert.assertEquals(msg, manufacturer, newManufacturer);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Manufacturer newManufacturer;
        id = manufacturerDAO.insert(manufacturer);
        manufacturer.setId(id);
        newManufacturer = manufacturerDAO.getById(manufacturer.getId());
        Assert.assertEquals(msg, manufacturer, newManufacturer);

        manufacturer.setName("new_name");
        manufacturer.setDescription("new_description");
        manufacturer.setLogo("new_logo");

        manufacturerDAO.update(manufacturer);
        newManufacturer = manufacturerDAO.getById(id);
        Assert.assertEquals(msg, manufacturer, newManufacturer);
        manufacturerDAO.deleteById(id);

        Throwable ex = null;
        try {
            manufacturerDAO.update(newManufacturer);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Manufacturer newManufacturer;

        id = manufacturerDAO.insert(manufacturer);
        manufacturer.setId(id);
        newManufacturer = manufacturerDAO.getById(manufacturer.getId());
        Assert.assertEquals(msg, manufacturer, newManufacturer);
        manufacturerDAO.deleteById(id);
        newManufacturer = manufacturerDAO.getById(id);
        Assert.assertNull(msg, newManufacturer);

        Throwable ex = null;
        try {
            manufacturerDAO.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Manufacturer> oldManufacturers;
        List<Manufacturer> newManufacturers;
        Manufacturer newManufacturer;
        Long id;

        oldManufacturers = manufacturerDAO.getAll();
        Assert.assertNotNull(msg, oldManufacturers);
        newManufacturer = new Manufacturer(manufacturer);
        newManufacturer.setName(String.valueOf(counter));
        counter += 1;
        id = manufacturerDAO.insert(manufacturer);
        manufacturer.setId(id);
        id = manufacturerDAO.insert(newManufacturer);
        newManufacturer.setId(id);

        newManufacturers = manufacturerDAO.getAll();
        Assert.assertEquals(msg, oldManufacturers.size() + 2, newManufacturers.size());
        Assert.assertTrue(msg, newManufacturers.contains(manufacturer));
        Assert.assertTrue(msg, newManufacturers.contains(newManufacturer));

        for (Manufacturer value : newManufacturers) {
            manufacturerDAO.deleteById(value.getId());
        }
        newManufacturers = manufacturerDAO.getAll();
        Assert.assertNotNull(msg, newManufacturers);
        Assert.assertTrue(msg, newManufacturers.size() == 0);
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = manufacturerDAO.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        manufacturerDAO.insert(manufacturer);
        newCount = manufacturerDAO.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Manufacturer> manufacturers;
        Manufacturer newManufacturer;
        Long id;

        manufacturers = manufacturerDAO.getAll();
        Assert.assertNotNull(msg, manufacturers);
        for (Manufacturer value : manufacturers) {
            manufacturerDAO.deleteById(value.getId());
        }
        manufacturers = manufacturerDAO.getAll();
        Assert.assertNotNull(msg, manufacturers);
        Assert.assertTrue(msg, manufacturers.size() == 0);
        for (int i = 0; i < 10; i++) {
            newManufacturer = new Manufacturer(manufacturer);
            newManufacturer.setName(String.valueOf(counter));
            counter += 1;
            id = manufacturerDAO.insert(newManufacturer);
            newManufacturer.setId(id);
            manufacturers.add(newManufacturer);
        }

        Assert.assertEquals(msg, manufacturerDAO.getByGap(0, 3), manufacturers.subList(0, 0 + 3));
        Assert.assertEquals(msg, manufacturerDAO.getByGap(3, 5), manufacturers.subList(3, 3 + 5));
        Assert.assertEquals(msg, manufacturerDAO.getByGap(2, 8), manufacturers.subList(2, 2 + 8));
        Assert.assertEquals(msg, manufacturerDAO.getByGap(6, 7), manufacturers.subList(6, manufacturers.size()));
        Assert.assertEquals(msg, manufacturerDAO.getByGap(-4, 3), manufacturers.subList(0, 0 + 3));
        Assert.assertEquals(msg, manufacturerDAO.getByGap(0, -1), manufacturers.subList(0, manufacturers.size()));
        Assert.assertEquals(msg, manufacturerDAO.getByGap(manufacturers.size(), 1), manufacturers.subList(0, 0));
    }
}