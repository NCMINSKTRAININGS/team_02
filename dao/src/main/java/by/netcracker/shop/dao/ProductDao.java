package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.Product;

import java.util.List;

public interface ProductDao {

    Product finById(int id);

    List<Product> findAll();

    void save(Product product);

    void deleteById(int id);
}
