package by.netcracker.shop.services;

import by.netcracker.shop.pojo.Product;

import java.util.List;

public interface ProductService {

    Product finById(int id);

    List<Product> findAll();

    void save(Product product);

    void update(Product product);

    void deleteById(int id);
}
