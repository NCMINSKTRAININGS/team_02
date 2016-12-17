package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.ProductImageDao;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
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
    public Long insert(ProductImage image) throws ServiceException {
        try {
            return dao.insert(image);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductImage getById(Long id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(ProductImage entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            return dao.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ProductImage> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
