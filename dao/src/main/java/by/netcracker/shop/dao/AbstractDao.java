package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractDao<T> implements DAO<T> {
    private Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected AbstractDao(Class persistentClass, SessionFactory sessionFactory){
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable insert(T entity) throws DAOException {
        Serializable id;
        try {
            Session session = getCurrentSession();
            session.saveOrUpdate(entity);
            id = session.getIdentifier(entity);
        }
        catch(HibernateException e) {
            throw new DAOException();
        }
        return id;
    }

    @Override
    public T getById(int id) throws DAOException{
        T entity;
        try {
            Session session = getCurrentSession();
            entity = session.get(persistentClass, id);
        }
        catch(HibernateException e){
            throw new DAOException();
        }
        return entity;
    }

    @Override
    public boolean update(T entity) throws DAOException{
        try {
            Session session = getCurrentSession();
            session.merge(entity);
        }
        catch(HibernateException e) {
            throw new DAOException();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DAOException{
        try {
            Session session = getCurrentSession();
            T entity = (T) session.get(persistentClass, id);
            session.delete(entity);
        }
        catch(HibernateException e){
            throw new DAOException(e.getMessage());
        }
        catch(IllegalArgumentException e){
            throw new DAOException();
        }
        return false;
    }
}
