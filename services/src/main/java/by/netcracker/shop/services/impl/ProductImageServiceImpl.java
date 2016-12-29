package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Product;
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
    private ProductDAO productDAO;

    @Autowired
    private ProductImageConverter converter;

    private static Logger logger = Logger.getLogger(ProductImageServiceImpl.class);

    @Override
    public void insert(ProductImageDTO imageDTO) throws ServiceException {
        ProductImage imagePOJO = null;
        Product productPOJO;
        try {
            if (imageDTO.getId() != null) {
                imagePOJO = dao.getById(imageDTO.getId());
            }
            if (imagePOJO == null)
                imagePOJO = new ProductImage();
            productPOJO = productDAO.getById(imageDTO.getProductId());
            dao.insert(converter.toImagePOJO(imageDTO, imagePOJO, productPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductImageDTO getById(Long id) throws ServiceException {
        ProductImage imagePOJO;
        try {
            imagePOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.toImageDTO(imagePOJO);
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
        List<ProductImage> imagePOJOs;
        List<ProductImageDTO> imageDTOs;
        try {
            imagePOJOs = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        imageDTOs = new ArrayList<>(imagePOJOs.size());
        for (ProductImage image : imagePOJOs) {
            imageDTOs.add(converter.toImageDTO(image));
        }
        return imageDTOs;
    }
}
