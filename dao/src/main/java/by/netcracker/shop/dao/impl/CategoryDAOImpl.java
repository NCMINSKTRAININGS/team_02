package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.pojo.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDAO")
public class CategoryDAOImpl extends AbstractDAO<Long, Category> implements CategoryDAO {

    public CategoryDAOImpl() {
        super();
    }
}
