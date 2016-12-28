package by.netcracker.shop.utils;

import by.netcracker.shop.dao.*;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.OrderProduct;
import by.netcracker.shop.pojo.OrderProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class OrderConverter  {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PaymentDAO paymentDAO;
    @Autowired
    private DeliveryDAO deliveryDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderProductDAO orderProductDAO;


    public OrderDTO convertToFront(Order order) {
        OrderDTO orderDto= new OrderDTO();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setComment(order.getComment());
        if(order.getDelivery()!=null){
            orderDto.setDeliveryId(order.getDelivery().getId());
        }else {orderDto.setDeliveryId(null);}

        if (order.getPayment()!=null){
            orderDto.setPaymentId(order.getPayment().getId());
        }else {orderDto.setPaymentId(null);}
        Set<OrderProduct>orderProducts= order.getOrderProducts();
        Set<Long> productsId =orderDto.getProductsId();
        for (OrderProduct orderProduct:orderProducts){
            productsId.add(orderProduct.getProduct().getId());
        }
        orderDto.setProductsId(productsId);
        orderDto.setPrice(order.getPrice());
        orderDto.setProduced(order.getProduced());

        return orderDto;
    }

    public Order convertToLocal(OrderDTO orderDto, Order order)  {
        try {
            order.setUser(userDAO.getById(orderDto.getUserId()));
            order.setPrice(orderDto.getPrice());
            order.setPayment(paymentDAO.getById(orderDto.getPaymentId()));
            order.setDelivery(deliveryDAO.getById(orderDto.getDeliveryId()));
            order.setComment(orderDto.getComment());
            Set<Long> productsId = orderDto.getProductsId();
            Set<OrderProduct> orderProducts = order.getOrderProducts();
            OrderProduct orderProduct;
            for (Long id:productsId){
                OrderProductId orderProductId = new OrderProductId(order,productDAO.getById(id));
                orderProducts.add(orderProductDAO.getById(orderProductId));
            }
            order.setProduced(orderDto.getProduced());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return order;
    }
}
