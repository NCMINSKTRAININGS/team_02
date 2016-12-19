package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO dao;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Long insert(User entity) throws ServiceException {
        return null;
    }

    @Override
    public User getById(Long id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User entity) throws ServiceException {

    }

    @Override
    public void deleteById(Long id) throws ServiceException {
    }

    @Override
    public List<User> getAll() throws ServiceException {
        return null;
    }
}
