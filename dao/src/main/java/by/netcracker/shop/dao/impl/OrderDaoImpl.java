package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.OrderDao;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Long,Order> implements OrderDao {
    public OrderDaoImpl() {
        super("order");
    }

    @Override
    public List<Order> getOrderByUser() throws DAOException {
        throw new DAOException();
    }
}