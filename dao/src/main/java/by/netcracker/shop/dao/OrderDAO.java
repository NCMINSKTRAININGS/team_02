package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;

import java.util.List;

public interface OrderDAO extends DAO<Order, Long> {

    List<Order> getOrdersByUser(User user) throws DAOException;

    List<Object[]> getGroupedOrders() throws DAOException;

}
