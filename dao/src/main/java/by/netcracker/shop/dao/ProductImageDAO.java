package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;

public interface ProductImageDAO extends DAO<ProductImage, Long> {

    ProductImage getImagesByProduct(Product product) throws DAOException;
}
