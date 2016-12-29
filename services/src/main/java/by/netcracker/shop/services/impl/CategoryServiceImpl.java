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
import java.util.LinkedList;
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
        Category categoryPOJO;
        try {
            categoryPOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.toCategoryDTO(categoryPOJO);
    }

    @Override
    public void update(CategoryDTO categoryDTO) throws ServiceException {
        Category categoryPOJO = null;
        try {
            if (categoryDTO.getId() != null)
                categoryPOJO = dao.getById(categoryDTO.getId());
            if (categoryPOJO == null) {
                throw new ServiceException();
            }
            categoryPOJO = converter.toCategoryPOJO(categoryDTO, categoryPOJO);
            dao.update(categoryPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Long insert(CategoryDTO categoryDTO) throws ServiceException {
        Category categoryPOJO = null;
        Long id;
        try {
            if (categoryDTO.getId() != null) {
                categoryPOJO = dao.getById(categoryDTO.getId());
            }
            if (categoryPOJO == null)
                categoryPOJO = new Category();
            id = dao.insert(converter.toCategoryPOJO(categoryDTO, categoryPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public List<CategoryDTO> getAll() throws ServiceException {
        List<Category> categoryPOJOs;
        List<CategoryDTO> categoryDTOs;
        try {
            categoryPOJOs = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        categoryDTOs = new ArrayList<>(categoryPOJOs.size());
        for (Category category : categoryPOJOs) {
            categoryDTOs.add(converter.toCategoryDTO(category));
        }
        return categoryDTOs;
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
    public List<CategoryDTO> getByGap(int offset, int quantity) throws ServiceException {
        List<Category> categoryPOJOs;
        List<CategoryDTO> categoryDTOs = new LinkedList<>();
        try {
            categoryPOJOs = dao.getByGap(offset, quantity);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (Category categoryPOJO : categoryPOJOs) {
            categoryDTOs.add(converter.toCategoryDTO(categoryPOJO));
        }
        return categoryDTOs;
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
