package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.OrderConverter;
import by.netcracker.shop.utils.ProductConverter;
import by.netcracker.shop.utils.UserConverter;
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
    private ProductDAO productDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserConverter userConverter;

   // private static Logger logger = Logger.getLogger(OrderServiceImpl.class);


    @Override
    public void insert(OrderDto order) throws ServiceException {
        try {
            if (order.getId() != null) {
                Order entity = dao.getById(order.getId());
                dao.insert(orderConverter.convertToLocal(order, entity));
            } else {
                dao.insert(orderConverter.convertToLocal(order, new Order()));
            }
        } catch (DAOException e) {
           // logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderDto getById(Long id) throws ServiceException {
        Order order;
        try {
            order = dao.getById(id);
        } catch (DAOException e) {
           // logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return orderConverter.convertToFront(order);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DAOException e) {
         //   logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDto> getAll() throws ServiceException {
        List<Order> orders;
        try {
            orders = dao.getAll();
        } catch (DAOException e) {
          //  logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<OrderDto> result = new ArrayList<>(orders.size());
        for (Order order : orders) {
            result.add(orderConverter.convertToFront(order));
        }
        return result;
    }

    @Override
    public List<OrderDto> getOrdersByUser(UserDTO userDTO) throws ServiceException {
        List<Order> orders;
        try {
            User user = userDAO.getByUsername(userDTO.getUsername());
            orders = dao.getOrdersByUser(user);
        } catch (DAOException e) {
          //  logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<OrderDto> result = new ArrayList<>(orders.size());
        for (Order order : orders) {
            result.add(orderConverter.convertToFront(order));
        }
        return result;
    }

    @Override
    public List<Object[]> getGroupedOrders() throws ServiceException {
        List<Object[]> mapList= new ArrayList<>();
        try {
            mapList = dao.getGroupedOrders();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    public void addProdToOrder(User user, ProductDTO product) throws ServiceException, DAOException {

    }
/*

    @Override
    public void addProdToOrder(User user, ProductDTO product) throws ServiceException, DAOException {

        List<OrderDto> usersOrders = getOrdersByUser(user);
        OrderDto order = usersOrders.get(usersOrders.size()-1);

        Product entityProd = productDAO.getById(product.getId());
        Product productConverted=productConverter.convertToLocal(product,entityProd);

        if(!order.getProducts().contains(productConverted)){
            order.getProducts().add(productConverted);
            order.setPrice(order.getPrice()+product.getPrice());
        }else {
            Set<Product> productSet= new HashSet<>();
            productSet.add(productConverted);
            OrderDto newOrder = new OrderDto();
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
    */
}
