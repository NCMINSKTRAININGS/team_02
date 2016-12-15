package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.ProductImageDao;
import by.netcracker.shop.pojo.ProductImage;
import by.netcracker.shop.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productImageService")
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao dao;

    @Override
    public ProductImage finById(int id) {
        return dao.finById(id);
    }

    @Override
    public List<ProductImage> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(ProductImage image) {
        dao.save(image);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
