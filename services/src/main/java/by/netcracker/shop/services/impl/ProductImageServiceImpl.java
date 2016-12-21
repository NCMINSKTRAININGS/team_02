package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.ProductImage;
import by.netcracker.shop.services.ProductImageService;
import by.netcracker.shop.utils.ProductImageConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("productImageService")
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDAO dao;

    @Autowired
    private ProductImageConverter converter;

    private static Logger logger = Logger.getLogger(ProductImageServiceImpl.class);

    @Override
    public void insert(ProductImageDTO image) throws ServiceException {
        try {
            if (image.getId() != null) {
                ProductImage productImage = dao.getById(image.getId());
                dao.insert(converter.convertToLocal(image, productImage));
            } else {
                dao.insert(converter.convertToLocal(image, new ProductImage()));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductImageDTO getById(Long id) throws ServiceException {
        ProductImage productImage;
        try {
            productImage = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.convertToFront(productImage);
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
    public List<ProductImageDTO> getAll() throws ServiceException {
        List<ProductImage> imageList;
        try {
            imageList = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<ProductImageDTO> dtoList = new ArrayList<>(imageList.size());
        for (ProductImage image : imageList) {
            dtoList.add(converter.convertToFront(image));
        }
        return dtoList;
    }
}
