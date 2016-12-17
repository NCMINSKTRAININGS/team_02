package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.pojo.User;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<Long, User> implements UserDAO {
    public UserDAOImpl() {
        super("user");
    }
}
