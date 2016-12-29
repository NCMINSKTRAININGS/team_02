package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.services.ProductService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ContextConfiguration("/test-services-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ManufacturerDAO manufacturerDAO;

    private static String assertMsg;
    private static int counter;
    private ProductDTO productDTO;
    private Category categoryPOJO;
    private Manufacturer manufacturerPOJO;
    private Set<String> images = new HashSet<>();

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from CategoryServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        categoryPOJO = new Category(null, String.valueOf(counter), "test", "test");
        manufacturerPOJO = new Manufacturer(null, String.valueOf(counter), "test", "test");
        categoryDAO.insert(categoryPOJO);
        manufacturerDAO.insert(manufacturerPOJO);
        productDTO = new ProductDTO(null, categoryPOJO.getId(), manufacturerPOJO.getId(), manufacturerPOJO.getName(),
                categoryPOJO.getName(), "test", "test", 0d, "test", 0, "test", images);
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        productDTO = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductDTO newProductDTO;
        id = productService.insert(productDTO);
        productDTO.setId(id);
        newProductDTO = productService.getById(productDTO.getId());
        Assert.assertEquals(msg, productDTO, newProductDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductDTO newProductDTO;
        id = productService.insert(productDTO);
        productDTO.setId(id);
        newProductDTO = productService.getById(productDTO.getId());
        Assert.assertEquals(msg, productDTO, newProductDTO);

        productDTO.setDescription("new_description");
        productDTO.setImage("new_image");

        productService.update(productDTO);
        newProductDTO = productService.getById(id);
        Assert.assertEquals(msg, productDTO, newProductDTO);
        productService.deleteById(id);
        ProductDTO newProduct = productService.getById(id);
        Assert.assertNull(msg, newProduct);

        Throwable ex = null;
        try {
            productService.update(newProductDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        ProductDTO newProduct;

        id = productService.insert(productDTO);
        productDTO.setId(id);
        newProduct = productService.getById(productDTO.getId());
        Assert.assertEquals(msg, productDTO, newProduct);
        productService.deleteById(id);
        newProduct = productService.getById(id);
        Assert.assertNull(msg, newProduct);

        Throwable ex = null;
        try {
            productService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ProductDTO> oldProducts;
        List<ProductDTO> newProducts;
        ProductDTO newProduct;
        Long id;

        oldProducts = productService.getAll();
        Assert.assertNotNull(msg, oldProducts);
        newProduct = new ProductDTO(productDTO);
        newProduct.setName(String.valueOf(counter));
        counter += 1;
        id = productService.insert(productDTO);
        productDTO.setId(id);
        id = productService.insert(newProduct);
        newProduct.setId(id);

        newProducts = productService.getAll();
        Assert.assertEquals(msg, oldProducts.size() + 2, newProducts.size());
        Assert.assertTrue(msg, newProducts.contains(productDTO));
        Assert.assertTrue(msg, newProducts.contains(newProduct));

        for (ProductDTO value : newProducts) {
            productService.deleteById(value.getId());
        }
        newProducts = productService.getAll();
        Assert.assertNotNull(msg, newProducts);
        Assert.assertTrue(msg, newProducts.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<ProductDTO> products;
        ProductDTO newProduct;
        Long id;

        products = productService.getAll();
        Assert.assertNotNull(msg, products);
        for (ProductDTO value : products) {
            productService.deleteById(value.getId());
        }
        products = productService.getAll();
        Assert.assertNotNull(msg, products);
        Assert.assertTrue(msg, products.size() == 0);
        for (int i = 0; i < 10; i++) {
            newProduct = new ProductDTO(productDTO);
            newProduct.setName(String.valueOf(counter));
            counter += 1;
            id = productService.insert(newProduct);
            newProduct.setId(id);
            products.add(newProduct);
        }

        Assert.assertEquals(msg, productService.getByGap(0, 3), products.subList(0, 0 + 3));
        Assert.assertEquals(msg, productService.getByGap(3, 5), products.subList(3, 3 + 5));
        Assert.assertEquals(msg, productService.getByGap(2, 8), products.subList(2, 2 + 8));
        Assert.assertEquals(msg, productService.getByGap(6, 7), products.subList(6, products.size()));
        Assert.assertEquals(msg, productService.getByGap(-4, 3), products.subList(0, 0 + 3));
        Assert.assertEquals(msg, productService.getByGap(0, -1), products.subList(0, products.size()));
        Assert.assertEquals(msg, productService.getByGap(products.size(), 1), products.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = productService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        productService.insert(productDTO);
        newCount = productService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}