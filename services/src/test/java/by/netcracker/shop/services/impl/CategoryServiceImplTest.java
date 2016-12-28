package by.netcracker.shop.services.impl;

import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.CategoryService;
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
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    private static String assertMsg;
    private CategoryDTO category;
    private static int counter;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from CategoryServiceImplTest failed: ";
        counter = 1;
    }

    @Before
    public void setUp() throws Exception {
        category = new CategoryDTO(null, String.valueOf(counter), "test", "test");
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
        CategoryDTO newCategoryDTO;
        id = categoryService.insert(category);
        category.setId(id);
        newCategoryDTO = categoryService.getById(category.getId());
        Assert.assertEquals(msg, category, newCategoryDTO);
    }

    @Test
    public void update() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        CategoryDTO newCategoryDTO;
        id = categoryService.insert(category);
        category.setId(id);
        newCategoryDTO = categoryService.getById(category.getId());
        Assert.assertEquals(msg, category, newCategoryDTO);

        category.setDescription("new_description");
        category.setImage("new_image");

        categoryService.update(category);
        newCategoryDTO = categoryService.getById(id);
        Assert.assertEquals(msg, category, newCategoryDTO);
        categoryService.deleteById(id);
        CategoryDTO newCategory = categoryService.getById(id);
        Assert.assertNull(msg, newCategory);

        Throwable ex = null;
        try {
            categoryService.update(newCategoryDTO);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void deleteById() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long id;
        CategoryDTO newCategory;

        id = categoryService.insert(category);
        category.setId(id);
        newCategory = categoryService.getById(category.getId());
        Assert.assertEquals(msg, category, newCategory);
        categoryService.deleteById(id);
        newCategory = categoryService.getById(id);
        Assert.assertNull(msg, newCategory);

        Throwable ex = null;
        try {
            categoryService.deleteById(id);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertTrue(msg, ex instanceof ServiceException);
    }

    @Test
    public void getAll() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<CategoryDTO> oldCategories;
        List<CategoryDTO> newCategories;
        CategoryDTO newCategory;
        Long id;

        oldCategories = categoryService.getAll();
        Assert.assertNotNull(msg, oldCategories);
        newCategory = new CategoryDTO(category);
        newCategory.setName(String.valueOf(counter));
        counter += 1;
        id = categoryService.insert(category);
        category.setId(id);
        id = categoryService.insert(newCategory);
        newCategory.setId(id);

        newCategories = categoryService.getAll();
        Assert.assertEquals(msg, oldCategories.size() + 2, newCategories.size());
        Assert.assertTrue(msg, newCategories.contains(category));
        Assert.assertTrue(msg, newCategories.contains(newCategory));

        for (CategoryDTO value : newCategories) {
            categoryService.deleteById(value.getId());
        }
        newCategories = categoryService.getAll();
        Assert.assertNotNull(msg, newCategories);
        Assert.assertTrue(msg, newCategories.size() == 0);
    }

    @Test
    public void getByGap() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        List<CategoryDTO> categories;
        CategoryDTO newCategory;
        Long id;

        categories = categoryService.getAll();
        Assert.assertNotNull(msg, categories);
        for (CategoryDTO value : categories) {
            categoryService.deleteById(value.getId());
        }
        categories = categoryService.getAll();
        Assert.assertNotNull(msg, categories);
        Assert.assertTrue(msg, categories.size() == 0);
        for (int i = 0; i < 10; i++) {
            newCategory = new CategoryDTO(category);
            newCategory.setName(String.valueOf(counter));
            counter += 1;
            id = categoryService.insert(newCategory);
            newCategory.setId(id);
            categories.add(newCategory);
        }

        Assert.assertEquals(msg, categoryService.getByGap(0, 3), categories.subList(0, 0 + 3));
        Assert.assertEquals(msg, categoryService.getByGap(3, 5), categories.subList(3, 3 + 5));
        Assert.assertEquals(msg, categoryService.getByGap(2, 8), categories.subList(2, 2 + 8));
        Assert.assertEquals(msg, categoryService.getByGap(6, 7), categories.subList(6, categories.size()));
        Assert.assertEquals(msg, categoryService.getByGap(-4, 3), categories.subList(0, 0 + 3));
        Assert.assertEquals(msg, categoryService.getByGap(0, -1), categories.subList(0, categories.size()));
        Assert.assertEquals(msg, categoryService.getByGap(categories.size(), 1), categories.subList(0, 0));
    }

    @Test
    public void getCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        Long count;
        Long newCount;
        count = categoryService.getCount();
        Assert.assertFalse(msg, count == null || count < 0);
        categoryService.insert(category);
        newCount = categoryService.getCount();
        Assert.assertFalse(msg, newCount == null || newCount < 0);
        Assert.assertTrue(msg, count + 1 == newCount);
    }
}