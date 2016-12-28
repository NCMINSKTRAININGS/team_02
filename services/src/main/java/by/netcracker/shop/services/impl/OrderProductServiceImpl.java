package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.OrderProductDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.OrderProduct;
import by.netcracker.shop.pojo.OrderProductId;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderProductService;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.OrderProductConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("orderProductService")
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductDAO orderProductDAO;
    @Autowired
    private OrderProductConverter orderProductConverter;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserDAO userDAO;

    private static Logger logger = Logger.getLogger(OrderProductServiceImpl.class);

    @Override
    public OrderProductDTO getById(OrderProductId id) throws ServiceException {
        OrderProduct orderProduct;
        try {
            orderProduct = orderProductDAO.getById(id);
        } catch (DAOException e) {
            // logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return orderProductConverter.convertToFront(orderProduct);
    }

    @Override
    public void insert(OrderProductDTO productDTO) throws ServiceException {

    }

    @Override
    public List<OrderProductDTO> getAll() throws ServiceException {
        List<OrderProduct> orderProducts;
        try {
            orderProducts = orderProductDAO.getAll();
        } catch (DAOException e) {
            //  logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<OrderProductDTO> result = new ArrayList<>(orderProducts.size());
        for (OrderProduct orderProduct : orderProducts) {
            result.add(orderProductConverter.convertToFront(orderProduct));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) throws ServiceException {

    }

    @Override
    public List<OrderProductDTO> getOrdersByUser(Long userId) throws ServiceException {
        User user= null;
        List<OrderProductDTO> orderProductDTO= new ArrayList<>();
        try {
            user = userDAO.getById(userId);
            for (OrderProduct orderProduct:
            orderProductDAO.getByUserId(userId)) {
                orderProductDTO.add(orderProductConverter.convertToFront(orderProduct));
            }

        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return orderProductDTO;
    }

}
