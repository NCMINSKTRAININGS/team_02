package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.ProductDao;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public Product finById(int id) {
        return dao.finById(id);
    }

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
