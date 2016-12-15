package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.ProductImage;

import java.util.List;

public interface ProductImageDao {

    ProductImage finById(int id);

    List<ProductImage> findAll();

    void save(ProductImage image);

    void deleteById(int id);
}
