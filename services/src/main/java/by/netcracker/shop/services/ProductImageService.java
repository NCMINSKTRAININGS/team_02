package by.netcracker.shop.services;

import by.netcracker.shop.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

    ProductImage finById(int id);

    List<ProductImage> findAll();

    void save(ProductImage image);

    void deleteById(int id);
}
