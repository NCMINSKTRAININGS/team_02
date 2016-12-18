package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO dao;

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public Long insert(Product product) throws ServiceException {
        Long id;
        try {
            id = dao.insert(product);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public Product getById(Long id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    public boolean update(Product entity) throws ServiceException {
        throw new ServiceException();
    }

    @Override
    public void updateEntity(Product product) throws ServiceException {
        Product p = null;
        try {
            p = dao.getById(product.getId());
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        if (p != null) {
            p.setCategoryId(product.getCategoryId());
            p.setManufacturerId(product.getManufacturerId());
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setKeywords(product.getKeywords());
            p.setQuantityInStock(product.getQuantityInStock());
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            return dao.deleteById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

}
