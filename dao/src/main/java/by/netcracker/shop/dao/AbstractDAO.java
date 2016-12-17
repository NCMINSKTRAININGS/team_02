package by.netcracker.shop.dao;

import by.netcracker.shop.constants.DAOConstants;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDAO<K extends Serializable, T extends AbstractEntity> implements DAO<T, K>{
    @Autowired
    private SessionFactory sessionFactory;

    private String table;
    private static Logger logger = Logger.getLogger(AbstractDAO.class);
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO(String table){
        this.persistentClass=(Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.table = table;
    }

    protected Session getSession(){
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
    public T getById(K key) throws DAOException {
        T entity;
        try {
            entity = (T) getSession().get(persistentClass, key);
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return entity;
    }

    @Override
    public boolean update(T entity) throws DAOException {
        throw new DAOException();
    }

    @Override
    public boolean deleteById(K id) throws DAOException {
        String str = "delete from " + table + " where id=:id";
        try {
            Query query = getSession().createSQLQuery(str);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return true;
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
}
