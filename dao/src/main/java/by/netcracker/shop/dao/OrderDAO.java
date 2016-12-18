package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order, Long> {
    List<Order> getOrderByUser() throws DAOException;
}
