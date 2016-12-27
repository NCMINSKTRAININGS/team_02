package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Category;
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
public class CategoryDAOImplTest {
    private static String assertMsg;
    private static int counter;
    @Autowired
    private CategoryDAO categoryDAO;
    private Category category;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from CategoryDAOImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        category = new Category(String.valueOf(counter), "test", "test");
        counter += 1;
    }

    @After
    public void tearDown() throws Exception {
        category = null;
    }

    @Test
    public void insert() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Category newCategory;
        id = categoryDAO.insert(category);
        category.setId(id);
        newCategory = categoryDAO.getById(category.getId());
        Assert.assertEquals(msg, category, newCategory);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Category newCategory;
        id = categoryDAO.insert(category);
        category.setId(id);
        newCategory = categoryDAO.getById(category.getId());
        Assert.assertEquals(msg, category, newCategory);

        category.setName("new_name");
        category.setDescription("new_description");
        category.setImage("new_image");

        categoryDAO.update(category);
        newCategory = categoryDAO.getById(id);
        Assert.assertEquals(msg, category, newCategory);
        categoryDAO.deleteById(id);

        Throwable ex = null;
        try {
            categoryDAO.update(newCategory);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        Category newCategory;

        id = categoryDAO.insert(category);
        category.setId(id);
        newCategory = categoryDAO.getById(category.getId());
        Assert.assertEquals(msg, category, newCategory);
        categoryDAO.deleteById(id);
        newCategory = categoryDAO.getById(id);
        Assert.assertNull(msg, newCategory);

        Throwable ex = null;
        try {
            categoryDAO.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof DAOException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Category> oldCategories;
        List<Category> newCategories;
        Category newCategory;
        Long id;

        oldCategories = categoryDAO.getAll();
        Assert.assertNotNull(msg, oldCategories);
        newCategory = new Category(category);
        newCategory.setName(String.valueOf(counter));
        counter += 1;
        id = categoryDAO.insert(category);
        category.setId(id);
        id = categoryDAO.insert(newCategory);
        newCategory.setId(id);

        newCategories = categoryDAO.getAll();
        Assert.assertEquals(msg, oldCategories.size() + 2, newCategories.size());
        Assert.assertTrue(msg, newCategories.contains(category));
        Assert.assertTrue(msg, newCategories.contains(newCategory));

        for (Category value : newCategories) {
            categoryDAO.deleteById(value.getId());
        }
        newCategories = categoryDAO.getAll();
        Assert.assertNotNull(msg, newCategories);
        Assert.assertTrue(msg, newCategories.size() == 0);
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = categoryDAO.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        categoryDAO.insert(category);
        newCount = categoryDAO.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<Category> categories;
        Category newCategory;
        Long id;

        categories = categoryDAO.getAll();
        Assert.assertNotNull(msg, categories);
        for (Category value : categories) {
            categoryDAO.deleteById(value.getId());
        }
        categories = categoryDAO.getAll();
        Assert.assertNotNull(msg, categories);
        Assert.assertTrue(msg, categories.size() == 0);
        for (int i = 0; i < 10; i++) {
            newCategory = new Category(category);
            newCategory.setName(String.valueOf(counter));
            counter += 1;
            id = categoryDAO.insert(newCategory);
            newCategory.setId(id);
            categories.add(newCategory);
        }

        Assert.assertEquals(msg, categoryDAO.getByGap(0, 3), categories.subList(0, 0 + 3));
        Assert.assertEquals(msg, categoryDAO.getByGap(3, 5), categories.subList(3, 3 + 5));
        Assert.assertEquals(msg, categoryDAO.getByGap(2, 8), categories.subList(2, 2 + 8));
        Assert.assertEquals(msg, categoryDAO.getByGap(6, 7), categories.subList(6, categories.size()));
        Assert.assertEquals(msg, categoryDAO.getByGap(-4, 3), categories.subList(0, 0 + 3));
        Assert.assertEquals(msg, categoryDAO.getByGap(0, -1), categories.subList(0, categories.size()));
        Assert.assertEquals(msg, categoryDAO.getByGap(categories.size(), 1), categories.subList(0, 0));
    }

}