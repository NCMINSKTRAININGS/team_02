package by.netcracker.shop.services;

import by.netcracker.shop.converter.Converter;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.pojo.Order;
import org.springframework.stereotype.Component;

/**
 * Created by j on 8.12.16.
 */
@Component
public class OrderConverter implements Converter<Order, OrderDto> {
    @Override
    public OrderDto convertToFront(Order order) {
        OrderDto orderDto= new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUserId());
        orderDto.setComment(order.getComment());
        orderDto.setDeliveryId(order.getDeliveryId());
        orderDto.setPaymentId(order.getPaymentId());
        orderDto.setPrice(order.getPrice());

        if (order.getProductsId().iterator().hasNext()){
             orderDto.setProductsId(order.getProductsId());
        }
        return orderDto;
    }

    @Override
    public Order converToLocal(OrderDto orderDto, Order order) {
        order.setUserId(orderDto.getUserId());
        order.setPrice(orderDto.getPrice());
        order.setPaymentId(orderDto.getPaymentId());
        order.setDeliveryId(orderDto.getDeliveryId());
        order.setComment(orderDto.getComment());

        if (orderDto.getProductsId().iterator().hasNext()){
            order.setProductsId(orderDto.getProductsId());
        }
        return order;
    }
}
