package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;

import java.util.List;

public interface ProductImageDAO extends DAO<ProductImage, Long> {

    List<ProductImage> getImagesByProduct(Product product) throws DAOException;
}
