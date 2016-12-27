package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.UserService;
import by.netcracker.shop.utils.UserConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.LinkedList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO dao;

    @Autowired
    private UserConverter userConverter;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Long insert(UserDTO user) throws ServiceException {
        Long id;
        User userPOJO = userConverter.convertToLocal(user, new User());
        try {
            id = dao.insert(userPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public UserDTO getById(Long id) throws ServiceException {
        User userPOJO;
        UserDTO userDTO;
        try {
            userPOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        userDTO = userConverter.convertToFront(userPOJO);
        return userDTO;
    }

    @Override
    public void update(UserDTO userDTO) throws ServiceException {
        User userPOJO = userConverter.convertToLocal(userDTO, new User());
        try {
            dao.update(userPOJO);
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
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> getAll() throws ServiceException {
        List<User> usersPOJO;
        List<UserDTO> usersDTO = new LinkedList<>();
        try {
            usersPOJO = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (User userPOJO : usersPOJO) {
            usersDTO.add(userConverter.convertToFront(userPOJO));
        }
        return usersDTO;
    }

    @Override
    public UserDTO getByUsername(String username) throws ServiceException {
        User user;
        try {
            user = dao.getByUsername(username);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return userConverter.convertToFront(user);
    }

    @Override
    public UserDTO getByUsernamePassword(String username, String password) throws ServiceException {
        User user;
        try {
            user = dao.getByUsernamePassword(username, password);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return userConverter.convertToFront(user);
    }
}
