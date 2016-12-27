package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Order;
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
    public Long insert(UserDTO userDTO) throws ServiceException {
        User userPOJO = null;
        List<Order> orderPOJOs = null;
        Long id;
        try {
            if (userDTO.getId() != null)
                userPOJO = dao.getById(userDTO.getId());
            if (userPOJO == null) {
                userPOJO = new User();
            } else orderPOJOs = userPOJO.getOrders();
            userPOJO = userConverter.toUserPOJO(userDTO, userPOJO, orderPOJOs);
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
        UserDTO userDTO = null;
        try {
            userPOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        if (userPOJO != null)
            userDTO = userConverter.toUserDTO(userPOJO);
        return userDTO;
    }

    @Override
    public void update(UserDTO userDTO) throws ServiceException {
        User userPOJO = null;
        try {
            if (userDTO.getId() != null)
                userPOJO = dao.getById(userDTO.getId());
            if (userPOJO == null) {
                throw new ServiceException();
            }
            userPOJO = userConverter.toUserPOJO(userDTO, userPOJO, userPOJO.getOrders());
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
        List<User> userPOJOs;
        List<UserDTO> userDTOs = new LinkedList<>();
        try {
            userPOJOs = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (User userPOJO : userPOJOs) {
            userDTOs.add(userConverter.toUserDTO(userPOJO));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getByUsername(String username) throws ServiceException {
        User userPOJO;
        try {
            userPOJO = dao.getByUsername(username);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return userConverter.toUserDTO(userPOJO);
    }

    @Override
    public UserDTO getByUsernamePassword(String username, String password) throws ServiceException {
        User userPOJO;
        try {
            userPOJO = dao.getByUsernamePassword(username, password);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return userConverter.toUserDTO(userPOJO);
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
    public List<UserDTO> getByGap(int offset, int quantity) throws ServiceException {
        List<User> userPOJOs;
        List<UserDTO> userDTOs = new LinkedList<>();
        try {
            userPOJOs = dao.getByGap(offset, quantity);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (User userPOJO : userPOJOs) {
            userDTOs.add(userConverter.toUserDTO(userPOJO));
        }
        return userDTOs;
    }
}
