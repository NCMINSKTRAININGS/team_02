package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDAO")
public class UserDAOImpl extends AbstractDao<Integer, User> implements UserDAO {
    @Override
    public User finById(int id) {
        return getByKey(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) createEntityCriteria().list();
    }

    @Override
    public void save(User product) {
        persist(product);
    }

    @Override
    public void deleteById(int id) {
        String hql = "delete from user where id=:id";
        getSession().createSQLQuery(hql).setParameter("id", id).executeUpdate();
    }
}
