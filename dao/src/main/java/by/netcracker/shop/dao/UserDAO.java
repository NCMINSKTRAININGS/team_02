package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.User;

import java.util.List;

public interface UserDAO {
    User finById(int id);

    List<User> findAll();

    void save(User product);

    void deleteById(int id);
}
