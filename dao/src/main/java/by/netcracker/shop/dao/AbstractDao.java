package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao< PK extends Serializable, T> {
    private static Logger logger = Logger.getLogger(AbstractDao.class);

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public  AbstractDao(){
        this.persistentClass=(Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public PK persist(T entity) {
//        getSession().persist(entity);
        PK id = null;
        try {
            Session session = getSession();
            session.saveOrUpdate(entity);
            id = (PK) session.getIdentifier(entity);
        }
        catch(HibernateException e) {
//            throw new DAOException();
        }
        return id;
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
}
