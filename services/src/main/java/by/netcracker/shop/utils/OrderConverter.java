package by.netcracker.shop.utils;

import by.netcracker.shop.dao.DeliveryDAO;
import by.netcracker.shop.dao.PaymentDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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

            order.setProduced(orderDto.getProduced());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return order;
    }
}
