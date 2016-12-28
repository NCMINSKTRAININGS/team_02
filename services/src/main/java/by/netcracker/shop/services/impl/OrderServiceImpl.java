package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.dto.UsersOrdersDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.utils.OrderConverter;
import by.netcracker.shop.utils.UsersOrdersConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO dao;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private UsersOrdersConverter usersOrdersConverter;


    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);


    @Override
    public void insert(OrderDTO orderDTO) throws ServiceException {
        Order orderPOJO = null;
        try {
            if (orderDTO.getId() != null) {
                orderPOJO = dao.getById(orderDTO.getId());
                dao.insert(orderConverter.convertToLocal(orderDTO, orderPOJO));
            } else {
                dao.insert(orderConverter.convertToLocal(orderDTO, new Order()));
            }
            if (orderPOJO == null)
                orderPOJO = new Order();
            dao.insert(orderConverter.convertToLocal(orderDTO, orderPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderDTO getById(Long id) throws ServiceException {
        Order orderPOJO;
        try {
            orderPOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return orderConverter.convertToFront(orderPOJO);
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
    public List<OrderDTO> getAll() throws ServiceException {
        List<Order> orderPOJOs;
        List<OrderDTO> orderDTOs;
        try {
            orderPOJOs = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        orderDTOs = new ArrayList<>(orderPOJOs.size());
        for (Order order : orderPOJOs) {
            orderDTOs.add(orderConverter.convertToFront(order));
        }
        return orderDTOs;
    }

    @Override
    public List<UsersOrdersDTO> getOrdersByUsers() throws ServiceException {
        List<User> users= null;
        List<UsersOrdersDTO> usersOrdersDTOs=new ArrayList<>();
        try {
            users = userDAO.getAll();
            for (User user:users) {
                usersOrdersDTOs.add(usersOrdersConverter.toUsersOrdersDTO(user,dao.getOrdersByUser(user)));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return usersOrdersDTOs;
    }

    @Override
    public UsersOrdersDTO getOrdersByUser(Long userId) throws ServiceException {
        User user= null;
        UsersOrdersDTO usersOrdersDTO;
        try {
            user = userDAO.getById(userId);
            usersOrdersDTO = (usersOrdersConverter.toUsersOrdersDTO(user,dao.getOrdersByUser(user)));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return usersOrdersDTO;
    }


}
