package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.OrderDao;
import by.netcracker.shop.pojo.Order;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Long,Order> implements OrderDao {

    private static Logger logger = Logger.getLogger(OrderDaoImpl.class);


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
        Query query =getSession().createSQLQuery("delete from 'order'  where id=:id ");
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
        List<Order> result = null;
        try {
            Query query =getSession().createSQLQuery("SELECT user_id, COUNT(*) FROM `order` GROUP BY  user_id ");
            query.setCacheable(true);
            result =query.list();
        }
        catch (HibernateException e){
            logger.error(e.getMessage(),e.getCause());
        }
        return result;


    }
}
