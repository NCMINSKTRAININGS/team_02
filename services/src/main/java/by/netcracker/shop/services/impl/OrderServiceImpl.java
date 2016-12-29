package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.dao.OrderProductDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.dto.UsersOrdersDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.*;
import by.netcracker.shop.services.OrderProductService;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.OrderConverter;
import by.netcracker.shop.utils.OrderProductConverter;
import by.netcracker.shop.utils.UsersOrdersConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO dao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private UsersOrdersConverter usersOrdersConverter;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private OrderProductService  orderProductService;
    @Autowired
    private OrderProductConverter orderProductConverter;


    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);


    @Override
    public Long insert(OrderDTO orderDTO) throws ServiceException {
        Order orderPOJO = null;
        Long id;
        try {
            if (orderDTO.getId() != null) {
                orderPOJO = dao.getById(orderDTO.getId());
                dao.insert(orderConverter.convertToLocal(orderDTO, orderPOJO));
            } else {
                dao.insert(orderConverter.convertToLocal(orderDTO, new Order()));
            }
            if (orderPOJO == null)
                orderPOJO = new Order();
           id = dao.insert(orderConverter.convertToLocal(orderDTO, orderPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return id;
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
                if(!user.getOrders().isEmpty()){
                usersOrdersDTOs.add(usersOrdersConverter.toUsersOrdersDTO(user,dao.getOrdersByUser(user)));
            }}
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

    @Override
    public void addToOrder(UserDTO userDTO, Long prodId) throws ServiceException {
        try {
            Product product =productDAO.getById(prodId);
            if(product.getQuantityInStock()>=1){
                User user =userDAO.getByUsername(userDTO.getUsername());
                List<Order> activeOrders = dao.getActiveOrderByUser(user);
                OrderProductId orderProductId = new OrderProductId();
                OrderProduct orderProduct = new OrderProduct();
            if(activeOrders.isEmpty()){

                Order order = new Order();
                order.setUser(user);
                order.setPrice(0d);
                order.setProduced(false);
                dao.insert(order);

                activeOrders =dao.getActiveOrderByUser(user);
                for(Order order1:activeOrders){
                    orderProductId.setOrder(order1);
                    break;
                }
                orderProductId.setProduct(product);
                orderProduct.setPrimaryKey(orderProductId);
                orderProduct.setPruductQuantity(1);
                orderProduct.setPrice(product.getPrice());
                orderProductDAO.insert(orderProduct);

                product.setQuantityInStock(product.getQuantityInStock()-1);
                productDAO.insert(product);

            }else {
                for (Order order:activeOrders){
                    orderProductId.setOrder(order);
                    break;
                }
                orderProductId.setProduct(product);
                orderProduct.setPrimaryKey(orderProductId);
                orderProduct = orderProductDAO.getById(orderProductId);
                if(orderProduct==null){
                    orderProduct = new OrderProduct();
                    orderProduct.setPrimaryKey(orderProductId);
                    orderProduct.setPruductQuantity(1);
                    orderProduct.setPrice(product.getPrice());
                    orderProductDAO.insert(orderProduct);
                }else {
                    orderProduct.setPrice(product.getPrice());
                    orderProduct.setPruductQuantity(orderProduct.getPruductQuantity() + 1);
                    orderProductDAO.insert(orderProduct);
                }
                product.setQuantityInStock(product.getQuantityInStock()-1);
                productDAO.insert(product);
            }
            }
        } catch (DAOException e) {
        e.printStackTrace();
    }
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
        public List<OrderDTO> getByGap(int offset, int quantity) throws ServiceException {
                    List<Order> orderPOJOs;
           List<OrderDTO> orderDTOs = new LinkedList<>();
            try {
                orderPOJOs = dao.getByGap(offset, quantity);
            } catch (DAOException e) {
                            logger.error(ServiceConstants.ERROR_SERVICE, e);
                     throw new ServiceException(e);
                    }
          for (Order orderPOJO : orderPOJOs) {
                          orderDTOs.add(orderConverter.convertToFront(orderPOJO));
                  }
             return orderDTOs;
            }

}
