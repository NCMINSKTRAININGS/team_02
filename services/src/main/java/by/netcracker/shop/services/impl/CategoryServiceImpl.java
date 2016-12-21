package by.netcracker.shop.services.impl;


import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.services.CategoryService;
import by.netcracker.shop.utils.CategoryConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO dao;

    @Autowired
    private CategoryConverter converter;

    private static Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Override
    public CategoryDTO getById(Long id) throws ServiceException {
        Category category;
        try {
            category = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.convertToFront(category);
    }

    @Override
    public void insert(CategoryDTO categoryDTO) throws ServiceException {
        try {
            if (categoryDTO.getId() != null) {
                Category category = dao.getById(categoryDTO.getId());
                dao.insert(converter.convertToLocal(categoryDTO, category));
            } else {
                dao.insert(converter.convertToLocal(categoryDTO, new Category()));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CategoryDTO> getAll() throws ServiceException {
        List<Category> categoryList;
        try {
            categoryList = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<CategoryDTO> dtoList = new ArrayList<>(categoryList.size());
        for (Category category : categoryList) {
            dtoList.add(converter.convertToFront(category));
        }
        return dtoList;
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }
}
