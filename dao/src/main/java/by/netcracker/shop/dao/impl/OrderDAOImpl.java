package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDAO")
public class OrderDAOImpl extends AbstractDAO<Long,Order> implements OrderDAO {
    public OrderDAOImpl() {
        super("order");
    }

    @Override
    public List<Order> getOrderByUser() throws DAOException {
        throw new DAOException();
    }
}