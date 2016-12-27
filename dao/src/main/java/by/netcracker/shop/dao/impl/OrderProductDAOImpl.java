package by.netcracker.shop.dao.impl;


import by.netcracker.shop.constants.DAOConstants;
import by.netcracker.shop.dao.OrderProductDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.OrderProduct;
import by.netcracker.shop.pojo.OrderProductId;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderProductDAO")
public class OrderProductDAOImpl implements OrderProductDAO{

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    private static Logger logger = Logger.getLogger(OrderProductDAOImpl.class);


    public OrderProductId insert(OrderProduct entity) throws DAOException {
        OrderProductId id;
        try {
            Session session = getSession();
            session.saveOrUpdate(entity);
            id = (OrderProductId) session.getIdentifier(entity);
        }
        catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return id;
    }


    public OrderProduct getById(OrderProductId id) throws DAOException {
        OrderProduct entity;
        if (id==null){
            return null;
        }else {
            try {
                Session session = getSession();
                entity = (OrderProduct) session.get(OrderProduct.class, id);
            } catch (HibernateException e) {
                logger.error(DAOConstants.ERROR_DAO, e);
                throw new DAOException(e);
            }
            return entity;
        }
    }


    public void deleteById(OrderProductId id) throws DAOException {
        try {
            Session session = getSession();
            OrderProduct entity = (OrderProduct) session.get(OrderProduct.class, id);
            session.delete(entity);
        } catch (HibernateException | IllegalArgumentException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
    }


    public List<OrderProduct> getAll() throws DAOException {
        List<OrderProduct> entities;
        try {
            entities = (List<OrderProduct>) getSession().createCriteria(OrderProduct.class).list();
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return entities;
    }


    public Long getCount() throws DAOException {
        Long result;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(OrderProduct.class);
            Projection count = Projections.rowCount();
            criteria.setProjection(count);
            result = (Long) criteria.uniqueResult();
        }
        catch(HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return result;
    }


    public List<OrderProduct> getByGap(int offset, int quantity) throws DAOException {
        List<OrderProduct> results;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(OrderProduct.class);
            criteria.setFirstResult(offset);
            criteria.setMaxResults(quantity);
            results = criteria.list();
        }
        catch(HibernateException e){
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return results;
    }

}
