package by.netcracker.shop.services;

import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Product;

public interface ProductService extends Service<Product, Long> {

    void updateEntity(Product product) throws ServiceException;
}
