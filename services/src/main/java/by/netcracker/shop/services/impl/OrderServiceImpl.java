package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.OrderConverter;
import by.netcracker.shop.utils.ProductConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO dao;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductService productService;

    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public void insert(OrderDTO orderDTO) throws ServiceException {
        Order orderPOJO = null;
        try {
            if (orderDTO.getId() != null) {
                orderPOJO = dao.getById(orderDTO.getId());
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
    public List<OrderDTO> getOrdersByUser(User userPOJO) throws ServiceException {
        List<Order> orderPOJOs;
        List<OrderDTO> orderDTOs;
        try {
            orderPOJOs = dao.getOrdersByUser(userPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
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
    public List<Object[]> getGroupedOrders() throws ServiceException {
        List<Object[]> list;
        try {
            list = dao.getGroupedOrders();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public void addProdToOrder(User user, ProductDTO product) throws ServiceException {
        //todo fixed this method
        List<OrderDTO> usersOrders = getOrdersByUser(user);
        OrderDTO order = usersOrders.get(usersOrders.size()-1);

        Product entityProd = null;
        try {
            entityProd = productDAO.getById(product.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Product productConverted=productConverter.convertToLocal(product,entityProd);

        if(!order.getProducts().contains(productConverted)){
            order.getProducts().add(productConverted);
            order.setPrice(order.getPrice()+product.getPrice());
        }else {
            Set<Product> productSet= new HashSet<>();
            productSet.add(productConverted);
            OrderDTO newOrder = new OrderDTO();
            newOrder.setUser(user);
            newOrder.setComment("");
            newOrder.setPrice(product.getPrice());
            newOrder.setProducts(productSet);
            insert(newOrder);
        }

        insert(order);

        product.setQuantityInStock(product.getQuantityInStock()-1);
        productService.insert(product);
    }
}
