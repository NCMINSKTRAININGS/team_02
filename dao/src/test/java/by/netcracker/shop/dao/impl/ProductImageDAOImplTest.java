package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
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
public class ProductImageDAOImplTest {
    private static String assertMsg;
    @Autowired
    private ProductImageDAO imageDAO;
    @Autowired
    private ProductDAO productDAO;
    private Product product;
    private ProductImage image;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from ProductImageDAOImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {
        product = new Product(null, null, "test", "test", 0d, "test", 0);
        productDAO.insert(product);
        image = new ProductImage(product, "test");
    }

    @After
    public void tearDown() throws Exception {
        image = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductImage newImage;
        id = imageDAO.insert(image);
        image.setId(id);
        newImage = imageDAO.getById(image.getId());
        Assert.assertEquals(msg, image, newImage);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductImage newImage;
        id = imageDAO.insert(image);
        image.setId(id);
        newImage = imageDAO.getById(image.getId());
        Assert.assertEquals(msg, image, newImage);

        image.setImage("new_image");

        imageDAO.update(image);
        newImage = imageDAO.getById(id);
        Assert.assertEquals(msg, image, newImage);
        imageDAO.deleteById(id);

        Throwable ex = null;
        try {
            imageDAO.update(newImage);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductImage newImage;

        id = imageDAO.insert(image);
        image.setId(id);
        newImage = imageDAO.getById(image.getId());
        Assert.assertEquals(msg, image, newImage);
        imageDAO.deleteById(id);
        newImage = imageDAO.getById(id);
        Assert.assertNull(msg, newImage);

        Throwable ex = null;
        try {
            imageDAO.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ProductImage> oldImages;
        List<ProductImage> newImages;
        ProductImage newImage;
        Long id;

        oldImages = imageDAO.getAll();
        Assert.assertNotNull(msg, oldImages);
        newImage = new ProductImage(image);
        id = imageDAO.insert(image);
        image.setId(id);
        id = imageDAO.insert(newImage);
        newImage.setId(id);

        newImages = imageDAO.getAll();
        Assert.assertEquals(msg, oldImages.size() + 2, newImages.size());
        Assert.assertTrue(msg, newImages.contains(image));
        Assert.assertTrue(msg, newImages.contains(newImage));

        for (ProductImage value : newImages) {
            imageDAO.deleteById(value.getId());
        }
        newImages = imageDAO.getAll();
        Assert.assertNotNull(msg, newImages);
        Assert.assertTrue(msg, newImages.size() == 0);
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = imageDAO.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        imageDAO.insert(image);
        newCount = imageDAO.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ProductImage> images;
        ProductImage newImage;
        Long id;

        images = imageDAO.getAll();
        Assert.assertNotNull(msg, images);
        for (ProductImage value : images) {
            imageDAO.deleteById(value.getId());
        }
        images = imageDAO.getAll();
        Assert.assertNotNull(msg, images);
        Assert.assertTrue(msg, images.size() == 0);
        for (int i = 0; i < 10; i++) {
            newImage = new ProductImage(image);
            id = imageDAO.insert(newImage);
            newImage.setId(id);
            images.add(newImage);
        }

        Assert.assertEquals(msg, imageDAO.getByGap(0, 3), images.subList(0, 0 + 3));
        Assert.assertEquals(msg, imageDAO.getByGap(3, 5), images.subList(3, 3 + 5));
        Assert.assertEquals(msg, imageDAO.getByGap(2, 8), images.subList(2, 2 + 8));
        Assert.assertEquals(msg, imageDAO.getByGap(6, 7), images.subList(6, images.size()));
        Assert.assertEquals(msg, imageDAO.getByGap(-4, 3), images.subList(0, 0 + 3));
        Assert.assertEquals(msg, imageDAO.getByGap(0, -1), images.subList(0, images.size()));
        Assert.assertEquals(msg, imageDAO.getByGap(images.size(), 1), images.subList(0, 0));
    }
}