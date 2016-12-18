package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.pojo.Product;
import org.springframework.stereotype.Repository;

@Repository("productDAO")
public class ProductDAOImpl extends AbstractDAO<Long, Product> implements ProductDAO {
    public ProductDAOImpl() {
        super();
    }
}
