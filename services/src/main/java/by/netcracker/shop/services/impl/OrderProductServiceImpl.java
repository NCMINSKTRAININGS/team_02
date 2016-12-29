package by.netcracker.shop.services.impl;

import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.dao.OrderProductDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.dto.OrderProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.OrderProduct;
import by.netcracker.shop.pojo.OrderProductId;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.services.OrderProductService;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.utils.OrderProductConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

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
    private OrderDAO orderDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDAO productDAO;
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
    public void deleteProductFromOrder(Long orderId, Long productId) throws ServiceException {
        try {
            Order order = orderDAO.getById(orderId);
            Product product = productDAO.getById(productId);
            OrderProductId id = new OrderProductId(order,product);
            OrderProduct orderProduct = orderProductDAO.getById(id);
            OrderDTO orderDTO = orderService.getById(orderId);
            Set<Long> productsId ;
            productsId = orderDTO.getProductsId();
            if(orderProduct.getPruductQuantity()>=2){
                orderProduct.setPruductQuantity(orderProduct.getPruductQuantity()-1);
            }else {
                orderProductDAO.deleteById(id);
                productsId.remove(productId);
                orderDTO.setProductsId(productsId);
            }
            product.setQuantityInStock(product.getQuantityInStock()+1);
            productDAO.insert(product);
            if(orderDTO.getProductsId().isEmpty()){
                orderService.deleteById(orderId);
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<OrderProductDTO> getOrdersByUser(Long userId) throws ServiceException {
        List<OrderProductDTO> orderProductDTO= new ArrayList<>();
        for (OrderProduct orderProduct:
        orderProductDAO.getByUserId(userId)) {
            orderProductDTO.add(orderProductConverter.convertToFront(orderProduct));
        }
        return orderProductDTO;
    }

    @Override
    public List<OrderProductDTO> getOrderByOrderId(Long orderId) throws ServiceException {
        List<OrderProductDTO> orderProductDTO = new ArrayList<>();
        for (OrderProduct orderProduct: orderProductDAO.getByOrderId(orderId)) {
            orderProductDTO.add(orderProductConverter.convertToFront(orderProduct));
        }
        return orderProductDTO;
    }

    @Override
    public Map<Long, List<OrderProductDTO>> separateByOrderId(List<OrderProductDTO> orderProductDTOs) throws ServiceException {
        Map<Long,List<OrderProductDTO>> map = new HashMap<>();
        for (OrderProductDTO orderProductDTO : orderProductDTOs) {
            List<OrderProductDTO> list =map.get(orderProductDTO.getOrderId());
            if (list == null){
                list = new ArrayList<>();
                map.put(orderProductDTO.getOrderId(),list);
            }
            list.add(orderProductDTO);
        }
        return map;
    }


}
