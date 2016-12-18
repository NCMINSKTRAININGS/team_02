package by.netcracker.shop.utils;

import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.pojo.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<Order, OrderDto> {
    @Override
    public OrderDto convertToFront(Order order) {
        OrderDto orderDto= new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUserId());
        orderDto.setComment(order.getComment());
        orderDto.setDelivery(order.getDelivery());
        orderDto.setPayment(order.getPayment());
        orderDto.setPrice(order.getPrice());

        if (order.getProducts().iterator().hasNext()){
             orderDto.setProducts(order.getProducts());
        }
        return orderDto;
    }

    @Override
    public Order converToLocal(OrderDto orderDto, Order order) {
        order.setUserId(orderDto.getUserId());
        order.setPrice(orderDto.getPrice());
        order.setPayment(orderDto.getPayment());
        order.setDelivery(orderDto.getDelivery());
        order.setComment(orderDto.getComment());

        if (orderDto.getProducts().iterator().hasNext()){
            order.setProducts(orderDto.getProducts());
        }
        return order;
    }
}
