package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.pojo.Product;
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
public class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    private Product product;

    @Autowired
    private CategoryDAO categoryDAO;

    private Category category;

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    private Manufacturer manufacturer;

    private static String assertMess;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMess = "() method from ProductDAOImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {
        product = new Product(null, null, "test", "test", 0, "test", 0);
        category = new Category("test", "test", "test");
        categoryDAO.insert(category);
        manufacturer = new Manufacturer("test", "test", "test");
        manufacturerDAO.insert(manufacturer);
    }

    @After
    public void tearDown() throws Exception {
        product = null;
        category = null;
        manufacturer = null;
    }

    @Test
    public void insert() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        Long id;
        Product newProduct;
        id = productDAO.insert(product);
        product.setId(id);
        newProduct = productDAO.getById(product.getId());
        Assert.assertEquals(mess, product, newProduct);
    }

    @Test
    public void getById() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        Long id;
        id = productDAO.insert(product);
        product.setId(id);
        Product currentProduct = productDAO.getById(product.getId());
        Assert.assertEquals(mess, product, currentProduct);
    }

    @Test
    public void update() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        Long id;
        Product newProduct;
        id = productDAO.insert(product);
        product.setId(id);
        newProduct = productDAO.getById(product.getId());
        Assert.assertEquals(mess, product, newProduct);
        productDAO.update(product);
        newProduct = productDAO.getById(product.getId());
        Assert.assertEquals(mess, product, newProduct);
        productDAO.deleteById(id);

        Throwable ex = null;
        try {
            productDAO.update(newProduct);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(mess, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        Long id;
        Product newProduct;
        id = productDAO.insert(product);
        product.setId(id);
        newProduct = productDAO.getById(product.getId());
        Assert.assertEquals(mess, product, newProduct);
        productDAO.deleteById(id);
        newProduct = productDAO.getById(id);
        Assert.assertNull(mess, newProduct);

        Throwable ex = null;
        try {
            productDAO.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(mess, ex instanceof DAOException);
    }

    @Test
    public void getAll() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        List<Product> products = productDAO.getAll();
        Assert.assertNotNull(mess, products);
    }

    @Test
    public void getCount() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        Long count;
        Long newCount;
        count = productDAO.getCount();
        Assert.assertFalse(mess, count == null || count < 0);
        productDAO.insert(product);
        newCount = productDAO.getCount();
        Assert.assertFalse(mess, newCount == null || newCount < 0);
        Assert.assertTrue(mess, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String mess = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMess;
        List<Product> products;
        Product newProduct;
        Long id;

        products = productDAO.getAll();
        Assert.assertNotNull(mess, products);
        for (Product value : products) {
            productDAO.deleteById(value.getId());
        }
        products = productDAO.getAll();
        Assert.assertNotNull(mess, products);
        Assert.assertTrue(mess, products.size() == 0);
        for (int i = 0; i < 10; i++) {
            newProduct = new Product(product);
            id = productDAO.insert(newProduct);
            newProduct.setId(id);
            products.add(newProduct);
        }

        Assert.assertEquals(mess, productDAO.getByGap(0, 3), products.subList(0, 0 + 3));
        Assert.assertEquals(mess, productDAO.getByGap(3, 5), products.subList(3, 3 + 5));
        Assert.assertEquals(mess, productDAO.getByGap(2, 8), products.subList(2, 2 + 8));
        Assert.assertEquals(mess, productDAO.getByGap(6, 7), products.subList(6, products.size()));
        Assert.assertEquals(mess, productDAO.getByGap(-4, 3), products.subList(0, 0 + 3));
        Assert.assertEquals(mess, productDAO.getByGap(0, -1), products.subList(0, products.size()));
        Assert.assertEquals(mess, productDAO.getByGap(products.size(), 1), products.subList(0, 0));
    }

}