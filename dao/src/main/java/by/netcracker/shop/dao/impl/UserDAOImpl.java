package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl extends AbstractDao<User> implements UserDAO {
    @Autowired
    public UserDAOImpl(SessionFactory factory){
        super(User.class, factory);
    }
}
