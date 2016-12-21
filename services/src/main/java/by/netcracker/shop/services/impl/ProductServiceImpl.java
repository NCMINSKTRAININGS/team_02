package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.ProductConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO dao;

    @Autowired
    private ProductConverter productConverter;

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public void insert(ProductDTO product) throws ServiceException {
        try {
            if (product.getId() != null) {
                Product product1 = dao.getById(product.getId());
                dao.insert(productConverter.convertToLocal(product, product1));
            } else {
                dao.insert(productConverter.convertToLocal(product, new Product()));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductDTO getById(Long id) throws ServiceException {
        Product product;
        try {
            product = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return productConverter.convertToFront(product);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ProductDTO> getAll() throws ServiceException {
        List<Product> products;
        try {
            products = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<ProductDTO> dtoList = new ArrayList<>(products.size());
        for (Product product : products) {
            dtoList.add(productConverter.convertToFront(product));
        }
        return dtoList;
    }

}
