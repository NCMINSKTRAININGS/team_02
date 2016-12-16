package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.OrderDao;
import by.netcracker.shop.pojo.Order;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Long,Order> implements OrderDao {
    @Override
    public void save(Order order) {
        persist(order);
    }

    @Override
    public Order findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void deleteOrderById(Long id) {
        Query query =getSession().createSQLQuery("delete from order  where id=:id ");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public List<Order> findAllOrders() {
        Criteria criteria = createEntityCriteria();
        return (List<Order>) criteria.list();
    }

    @Override
    public List<Order> findGroupedByUser() {
        return null;
    }
}