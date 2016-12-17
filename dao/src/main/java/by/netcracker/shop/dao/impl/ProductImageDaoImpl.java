package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.ProductImageDao;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Repository;

@Repository("productImageDao")
public class ProductImageDaoImpl extends AbstractDao<Long, ProductImage> implements ProductImageDao {
    public ProductImageDaoImpl() {
        super("product_image");
    }
}
