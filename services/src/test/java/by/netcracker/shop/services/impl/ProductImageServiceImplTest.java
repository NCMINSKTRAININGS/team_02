package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.ProductImageService;
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
public class ProductImageServiceImplTest {
    @Autowired
    private ProductImageService imageService;
    @Autowired
    private ProductDAO productDAO;

    private static String assertMsg;
    private ProductImageDTO image;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from ProductImageServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        image = new ProductImageDTO(null, null, "test", "test");
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        image = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductImageDTO newImageDTO;
        id = imageService.insert(image);
        image.setId(id);
        newImageDTO = imageService.getById(image.getId());
        Assert.assertEquals(msg, image, newImageDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductImageDTO newImageDTO;
        id = imageService.insert(image);
        image.setId(id);
        newImageDTO = imageService.getById(image.getId());
        Assert.assertEquals(msg, image, newImageDTO);

        image.setImage("new_image");

        imageService.update(image);
        newImageDTO = imageService.getById(id);
        Assert.assertEquals(msg, image, newImageDTO);
        imageService.deleteById(id);
        ProductImageDTO newImage = imageService.getById(id);
        Assert.assertNull(msg, newImage);

        Throwable ex = null;
        try {
            imageService.update(newImageDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductImageDTO newImage;

        id = imageService.insert(image);
        image.setId(id);
        newImage = imageService.getById(image.getId());
        Assert.assertEquals(msg, image, newImage);
        imageService.deleteById(id);
        newImage = imageService.getById(id);
        Assert.assertNull(msg, newImage);

        Throwable ex = null;
        try {
            imageService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ProductImageDTO> oldImages;
        List<ProductImageDTO> newImages;
        ProductImageDTO newImage;
        Long id;

        oldImages = imageService.getAll();
        Assert.assertNotNull(msg, oldImages);
        newImage = new ProductImageDTO(image);
        id = imageService.insert(image);
        image.setId(id);
        id = imageService.insert(newImage);
        newImage.setId(id);

        newImages = imageService.getAll();
        Assert.assertEquals(msg, oldImages.size() + 2, newImages.size());
        Assert.assertTrue(msg, newImages.contains(image));
        Assert.assertTrue(msg, newImages.contains(newImage));

        for (ProductImageDTO value : newImages) {
            imageService.deleteById(value.getId());
        }
        newImages = imageService.getAll();
        Assert.assertNotNull(msg, newImages);
        Assert.assertTrue(msg, newImages.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ProductImageDTO> images;
        ProductImageDTO newImage;
        Long id;

        images = imageService.getAll();
        Assert.assertNotNull(msg, images);
        for (ProductImageDTO value : images) {
            imageService.deleteById(value.getId());
        }
        images = imageService.getAll();
        Assert.assertNotNull(msg, images);
        Assert.assertTrue(msg, images.size() == 0);
        for (int i = 0; i < 10; i++) {
            newImage = new ProductImageDTO(image);
            id = imageService.insert(newImage);
            newImage.setId(id);
            images.add(newImage);
        }

        Assert.assertEquals(msg, imageService.getByGap(0, 3), images.subList(0, 0 + 3));
        Assert.assertEquals(msg, imageService.getByGap(3, 5), images.subList(3, 3 + 5));
        Assert.assertEquals(msg, imageService.getByGap(2, 8), images.subList(2, 2 + 8));
        Assert.assertEquals(msg, imageService.getByGap(6, 7), images.subList(6, images.size()));
        Assert.assertEquals(msg, imageService.getByGap(-4, 3), images.subList(0, 0 + 3));
        Assert.assertEquals(msg, imageService.getByGap(0, -1), images.subList(0, images.size()));
        Assert.assertEquals(msg, imageService.getByGap(images.size(), 1), images.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = imageService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        imageService.insert(image);
        newCount = imageService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}