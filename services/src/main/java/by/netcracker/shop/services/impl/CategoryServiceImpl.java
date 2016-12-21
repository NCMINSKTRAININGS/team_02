package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.services.CategoryService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dmitry
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO dao;

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public Long insert(Category category) throws ServiceException {
        Long id;
        try {
            id = dao.insert(category);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
        return id;
    }

    @Override
    public Category getById(Long id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void update(Category category) throws ServiceException {
        try {
            dao.update(category);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Category> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

}
