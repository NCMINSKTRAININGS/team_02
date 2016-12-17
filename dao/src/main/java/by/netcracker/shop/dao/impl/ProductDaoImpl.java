package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.ProductDao;
import by.netcracker.shop.pojo.Product;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {
    public ProductDaoImpl() {
        super("product");
    }
}
