package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.User;

public interface UserDAO extends DAO<User, Long> {
    User getByUsername(String username) throws DAOException;

    User getByUsernamePasswordSalt(String username, String password, String salt) throws DAOException;


}
