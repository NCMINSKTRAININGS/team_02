package by.netcracker.shop.dao.impl;

import by.netcracker.shop.constants.DAOConstants;
import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.OrderDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDAO")
public class OrderDAOImpl extends AbstractDAO<Long,Order> implements OrderDAO {
    private static Logger logger = Logger.getLogger(OrderDAOImpl.class);
    private static String GROUPED_ORDERS ="SELECT   user_id, username,  COUNT(*) FROM `order` " +
            "INNER JOIN user ON `order`.user_id = user.id GROUP BY  user_id";


    public OrderDAOImpl() {
        super();
    }

    @Override
    public List<Order> getOrdersByUser(User user) throws DAOException {
        List<Order> orders;
        try {
            Criteria criteria = getSession().createCriteria(Order.class);
            criteria.add(Restrictions.eq("user", user));
            orders = criteria.list();
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return orders;
    }

    @Override
    public List<Object[]> getGroupedOrders() throws DAOException {
        List<Object[]> result;
        try {
            Session session = getSession();
            result = session.createSQLQuery(GROUPED_ORDERS).list();
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return result;
    }
}