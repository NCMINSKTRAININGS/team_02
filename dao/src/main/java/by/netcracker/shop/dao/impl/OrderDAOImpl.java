package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderDAO")
public class OrderDAOImpl extends AbstractDAO<Long,Order> implements OrderDAO {
    public OrderDAOImpl() {
        super();
    }

    @Override
    public List<Order> getOrdersByUser(User user) throws DAOException {
        List<Order> orders;
        Criteria criteria= getSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("user",user));
        orders=criteria.list();
        return orders;
    }

    @Override
    public List<Order> getActiveOrderByUser(User user) throws DAOException {
        List<Order> orders;
        Criteria criteria= getSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("user",user));
        criteria.add(Restrictions.eq("isProduced",false));
        orders=criteria.list();
        return orders;
    }

    @Override
    public List<Object[]> getGroupedOrders() throws DAOException {
        String sql="select user_id,username, COUNT(*) from `order` INNER JOIN user ON `order`.user_id = user.id WHERE produced=0 GROUP BY user_id";
        return getSession().createSQLQuery(sql).list();
    }


}