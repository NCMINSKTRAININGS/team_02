package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.User;

import java.util.List;

public interface UserDAO {
    User finById(Long id);

    List<User> findAll();

    Long save(User product);

    void deleteById(int id);
}
