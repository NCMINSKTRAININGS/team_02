package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
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

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public void insert(ProductDTO productDTO) throws ServiceException {
        Product productPOJO = null;
        Category categoryPOJO;
        Manufacturer manufacturerPOJO;
        try {
            if (productDTO.getId() != null) {
                productPOJO = dao.getById(productDTO.getId());
            }
            if (productPOJO == null)
                productPOJO = new Product();
            categoryPOJO = categoryDAO.getById(productDTO.getCategoryId());
            manufacturerPOJO = manufacturerDAO.getById(productDTO.getManufacturerId());
            productPOJO = productConverter.convertToLocal(productDTO, productPOJO, categoryPOJO, manufacturerPOJO);
            dao.insert(productPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductDTO getById(Long id) throws ServiceException {
        Product productPOJO;
        try {
            productPOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return productConverter.convertToFront(productPOJO);
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
        List<Product> productPOJOs;
        List<ProductDTO> productDTOs;
        try {
            productPOJOs = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        productDTOs = new ArrayList<>(productPOJOs.size());
        for (Product product : productPOJOs) {
            productDTOs.add(productConverter.convertToFront(product));
        }
        return productDTOs;
    }

}
