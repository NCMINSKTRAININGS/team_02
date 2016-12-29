package by.netcracker.shop.dao;

import by.netcracker.shop.constants.DAOConstants;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDAO<K extends Serializable, T extends AbstractEntity<K>> implements DAO<T, K>{
    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(AbstractDAO.class);
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.persistentClass=(Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    @Override
    public K insert(T entity) throws DAOException {
        K id;
        try {
            Session session = getSession();
            session.saveOrUpdate(entity);
            id = (K) session.getIdentifier(entity);
        }
        catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(K id) throws DAOException {
        T entity;
        if (id==null){
            return null;
        }else {
        try {
            Session session = getSession();
            entity = (T) session.get(persistentClass, id);
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return entity;
    }}

    @Override
    public void update(T entity) throws DAOException {
        try {
            Session session = getSession();
            session.merge(entity);
        }
        catch(HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
    }

    @Override
    public void deleteById(K id) throws DAOException {
        try {
            Session session = getSession();
            T entity = (T) session.get(persistentClass, id);
            session.delete(entity);
        } catch (HibernateException | IllegalArgumentException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> entities;
        try {
            entities = (List<T>) createEntityCriteria().list();
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return entities;
    }

    @Override
    public Long getCount() throws DAOException {
        Long result;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(persistentClass);
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


    @Override
    public List<T> getByGap(int offset, int quantity) throws DAOException {
        List<T> results;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(persistentClass);
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
