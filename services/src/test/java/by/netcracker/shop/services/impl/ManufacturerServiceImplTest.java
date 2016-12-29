package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.ManufacturerService;
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
public class ManufacturerServiceImplTest {
    @Autowired
    private ManufacturerService manufacturerService;

    private static String assertMsg;
    private ManufacturerDTO manufacturer;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from ManufacturerServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        manufacturer = new ManufacturerDTO(null, String.valueOf(counter), "test", "test");
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
        ManufacturerDTO newManufacturerDTO;
        id = manufacturerService.insert(manufacturer);
        manufacturer.setId(id);
        newManufacturerDTO = manufacturerService.getById(manufacturer.getId());
        Assert.assertEquals(msg, manufacturer, newManufacturerDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ManufacturerDTO newManufacturerDTO;
        id = manufacturerService.insert(manufacturer);
        manufacturer.setId(id);
        newManufacturerDTO = manufacturerService.getById(manufacturer.getId());
        Assert.assertEquals(msg, manufacturer, newManufacturerDTO);

        manufacturer.setDescription("new_firstName");
        manufacturer.setLogo("new_lastName");

        manufacturerService.update(manufacturer);
        newManufacturerDTO = manufacturerService.getById(id);
        Assert.assertEquals(msg, manufacturer, newManufacturerDTO);
        manufacturerService.deleteById(id);
        ManufacturerDTO newManufacturer = manufacturerService.getById(id);
        Assert.assertNull(msg, newManufacturer);

        Throwable ex = null;
        try {
            manufacturerService.update(newManufacturerDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ManufacturerDTO newManufacturer;

        id = manufacturerService.insert(manufacturer);
        manufacturer.setId(id);
        newManufacturer = manufacturerService.getById(manufacturer.getId());
        Assert.assertEquals(msg, manufacturer, newManufacturer);
        manufacturerService.deleteById(id);
        newManufacturer = manufacturerService.getById(id);
        Assert.assertNull(msg, newManufacturer);

        Throwable ex = null;
        try {
            manufacturerService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ManufacturerDTO> oldManufacturers;
        List<ManufacturerDTO> newManufacturers;
        ManufacturerDTO newManufacturer;
        Long id;

        oldManufacturers = manufacturerService.getAll();
        Assert.assertNotNull(msg, oldManufacturers);
        newManufacturer = new ManufacturerDTO(manufacturer);
        newManufacturer.setName(String.valueOf(counter));
        counter += 1;
        id = manufacturerService.insert(manufacturer);
        manufacturer.setId(id);
        id = manufacturerService.insert(newManufacturer);
        newManufacturer.setId(id);

        newManufacturers = manufacturerService.getAll();
        Assert.assertEquals(msg, oldManufacturers.size() + 2, newManufacturers.size());
        Assert.assertTrue(msg, newManufacturers.contains(manufacturer));
        Assert.assertTrue(msg, newManufacturers.contains(newManufacturer));

        for (ManufacturerDTO value : newManufacturers) {
            manufacturerService.deleteById(value.getId());
        }
        newManufacturers = manufacturerService.getAll();
        Assert.assertNotNull(msg, newManufacturers);
        Assert.assertTrue(msg, newManufacturers.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ManufacturerDTO> manufacturers;
        ManufacturerDTO newManufacturer;
        Long id;

        manufacturers = manufacturerService.getAll();
        Assert.assertNotNull(msg, manufacturers);
        for (ManufacturerDTO value : manufacturers) {
            manufacturerService.deleteById(value.getId());
        }
        manufacturers = manufacturerService.getAll();
        Assert.assertNotNull(msg, manufacturers);
        Assert.assertTrue(msg, manufacturers.size() == 0);
        for (int i = 0; i < 10; i++) {
            newManufacturer = new ManufacturerDTO(manufacturer);
            newManufacturer.setName(String.valueOf(counter));
            counter += 1;
            id = manufacturerService.insert(newManufacturer);
            newManufacturer.setId(id);
            manufacturers.add(newManufacturer);
        }

        Assert.assertEquals(msg, manufacturerService.getByGap(0, 3), manufacturers.subList(0, 0 + 3));
        Assert.assertEquals(msg, manufacturerService.getByGap(3, 5), manufacturers.subList(3, 3 + 5));
        Assert.assertEquals(msg, manufacturerService.getByGap(2, 8), manufacturers.subList(2, 2 + 8));
        Assert.assertEquals(msg, manufacturerService.getByGap(6, 7), manufacturers.subList(6, manufacturers.size()));
        Assert.assertEquals(msg, manufacturerService.getByGap(-4, 3), manufacturers.subList(0, 0 + 3));
        Assert.assertEquals(msg, manufacturerService.getByGap(0, -1), manufacturers.subList(0, manufacturers.size()));
        Assert.assertEquals(msg, manufacturerService.getByGap(manufacturers.size(), 1), manufacturers.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = manufacturerService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        manufacturerService.insert(manufacturer);
        newCount = manufacturerService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}