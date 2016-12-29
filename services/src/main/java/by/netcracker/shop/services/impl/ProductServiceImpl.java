package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.ProductConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.LinkedList;
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

    @Autowired
    private ProductImageDAO imageDAO;

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public Long insert(ProductDTO productDTO) throws ServiceException {
        Product productPOJO = null;
        Category categoryPOJO;
        Manufacturer manufacturerPOJO;
        Long id;
        try {
            if (productDTO.getId() != null) {
                productPOJO = dao.getById(productDTO.getId());
            }
            if (productPOJO == null)
                productPOJO = new Product();
            categoryPOJO = categoryDAO.getById(productDTO.getCategoryId());
            manufacturerPOJO = manufacturerDAO.getById(productDTO.getManufacturerId());
            productPOJO = productConverter.toProductPOJO(productDTO, productPOJO, categoryPOJO, manufacturerPOJO);
            id = dao.insert(productPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public ProductDTO getById(Long id) throws ServiceException {
        Product productPOJO;
        List<ProductImage> imagePOJOs;
        ProductImage image = null;
        try {
            productPOJO = dao.getById(id);
            imagePOJOs = imageDAO.getImagesByProduct(productPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        if (imagePOJOs != null && imagePOJOs.size() != 0)
            image = imagePOJOs.iterator().next();
        return productConverter.toProductDTO(productPOJO, image);
    }

    @Override
    public void update(ProductDTO productDTO) throws ServiceException {
        Product productPOJO = null;
        Category categoryPOJO;
        Manufacturer manufacturerPOJO;
        try {
            if (productDTO.getId() != null)
                productPOJO = dao.getById(productDTO.getId());
            if (productPOJO == null) {
                throw new ServiceException();
            }
            categoryPOJO = categoryDAO.getById(productDTO.getCategoryId());
            manufacturerPOJO = manufacturerDAO.getById(productDTO.getManufacturerId());
            productPOJO = productConverter.toProductPOJO(productDTO, productPOJO, categoryPOJO, manufacturerPOJO);
            dao.update(productPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
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
        List<ProductImage> imagePOJOs;
        ProductImage image = null;
        try {
            productPOJOs = dao.getAll();
            productDTOs = new ArrayList<>(productPOJOs.size());
            for (Product product : productPOJOs) {
                imagePOJOs = imageDAO.getImagesByProduct(product);
                if (imagePOJOs != null && imagePOJOs.size() != 0)
                    image = imagePOJOs.iterator().next();
                productDTOs.add(productConverter.toProductDTO(product, image));
                image = null;
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return productDTOs;
    }

    @Override
    public Long getCount() throws ServiceException {
        Long count;
        try {
            count = dao.getCount();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public List<ProductDTO> getByGap(int offset, int quantity) throws ServiceException {
        List<Product> productPOJOs;
        List<ProductDTO> productDTOs = new LinkedList<>();
        List<ProductImage> imagePOJOs;
        ProductImage image = null;
        try {
            productPOJOs = dao.getByGap(offset, quantity);
            for (Product productPOJO : productPOJOs) {
                imagePOJOs = imageDAO.getImagesByProduct(productPOJO);
                if (imagePOJOs != null && imagePOJOs.size() != 0)
                    image = imagePOJOs.iterator().next();
                productDTOs.add(productConverter.toProductDTO(productPOJO, image));
                image = null;
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return productDTOs;
    }

}
