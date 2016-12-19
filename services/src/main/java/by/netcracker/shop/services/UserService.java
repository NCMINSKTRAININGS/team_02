package by.netcracker.shop.services;

import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;

public interface UserService extends Service<User,Long> {
    User getByUsername(String username) throws ServiceException;

    User getByUsernamePasswordSalt(String username, String password, String salt) throws ServiceException;
}
