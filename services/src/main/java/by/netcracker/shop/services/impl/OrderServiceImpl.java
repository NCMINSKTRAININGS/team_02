package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.utils.OrderConverter;
import by.netcracker.shop.services.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO dao;
    @Autowired
    private OrderConverter orderConverter;

    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public void insert(OrderDto order) throws ServiceException {
        try {
            if (order.getId() != null) {
                Order entity = dao.getById(order.getId());
                dao.insert(orderConverter.converToLocal(order, entity));
            } else {
                dao.insert(orderConverter.converToLocal(order, new Order()));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderDto getById(Long id) throws ServiceException {
        Order order = null;
        try {
            order = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return orderConverter.convertToFront(order);
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
    public List<OrderDto> getAll() throws ServiceException {
        List<Order> orders = null;
        try {
            orders = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        List<OrderDto> result=new ArrayList<>(orders.size());
        for (Order order: orders) {
            result.add(orderConverter.convertToFront(order));
        }
        return result;
    }
}
