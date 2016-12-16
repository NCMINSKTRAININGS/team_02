package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.Order;

import java.util.List;

/**
 * Created by j on 7.12.16.
 */
public interface OrderDao {
    void save(Order order);
    Order findById(Long id);
    void deleteOrderById(Long id);
    List<Order> findAllOrders();
    List <Order> findGroupedByUser();
}
