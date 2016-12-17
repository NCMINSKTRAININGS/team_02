package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Repository;

@Repository("productImageDAO")
public class ProductImageDAOImpl extends AbstractDAO<Long, ProductImage> implements ProductImageDAO {
    public ProductImageDAOImpl() {
        super("product_image");
    }
}
