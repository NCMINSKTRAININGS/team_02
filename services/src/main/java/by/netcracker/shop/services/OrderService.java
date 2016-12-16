package by.netcracker.shop.services;

import by.netcracker.shop.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void save(OrderDto order);

    OrderDto findById(Long id);

    void deleteOrderById(Long id);

    List<OrderDto> findAllOrders();
}
