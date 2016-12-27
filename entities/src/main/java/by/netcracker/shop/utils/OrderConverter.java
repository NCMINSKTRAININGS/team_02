package by.netcracker.shop.utils;

import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.pojo.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO;
        if (order == null)
            return null;
        orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser(order.getUser());
        orderDTO.setComment(order.getComment());
        orderDTO.setDelivery(order.getDelivery());
        orderDTO.setPayment(order.getPayment());
        orderDTO.setPrice(order.getPrice());

        if (order.getProducts().iterator().hasNext()){
             orderDTO.setProducts(order.getProducts());
        }
        return orderDTO;
    }

    public Order toOrderPOJO(OrderDTO orderDTO, Order order) {
        if (orderDTO == null || order == null)
            return null;
        order.setUser(orderDTO.getUser());
        order.setPrice(orderDTO.getPrice());
        order.setPayment(orderDTO.getPayment());
        order.setDelivery(orderDTO.getDelivery());
        order.setComment(orderDTO.getComment());

        if (orderDTO.getProducts().iterator().hasNext()){
            order.setProducts(orderDTO.getProducts());
        }
        return order;
    }
}
