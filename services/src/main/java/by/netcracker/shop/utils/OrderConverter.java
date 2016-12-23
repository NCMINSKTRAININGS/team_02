package by.netcracker.shop.utils;

import by.netcracker.shop.dao.DeliveryDAO;
import by.netcracker.shop.dao.PaymentDAO;
import by.netcracker.shop.dao.ProductDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderConverter implements Converter<Order, OrderDto> {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PaymentDAO paymentDAO;
    @Autowired
    private DeliveryDAO deliveryDAO;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public OrderDto convertToFront(Order order) {
        OrderDto orderDto= new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setId(order.getUser().getId());
        orderDto.setComment(order.getComment());
        orderDto.setDeliveryId(order.getDelivery().getId());
        orderDto.setPaymentId(order.getPayment().getId());
        orderDto.setPrice(order.getPrice());

        for (Product product: order.getProducts()) {
            orderDto.getProductsId().add(product.getId());
        }

        return orderDto;
    }

    @Override
    public Order convertToLocal(OrderDto orderDto, Order order)  {
        try {
            order.setUser(userDAO.getById(orderDto.getUserId()));
            order.setPrice(orderDto.getPrice());
            order.setPayment(paymentDAO.getById(orderDto.getPaymentId()));
            order.setDelivery(deliveryDAO.getById(orderDto.getDeliveryId()));
            order.setComment(orderDto.getComment());
            for (Long productId :orderDto.getProductsId()){
                order.getProducts().add(productDAO.getById(productId));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return order;
    }
}
