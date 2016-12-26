package by.netcracker.shop.services;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;

public interface UserService extends Service<User,Long> {
    UserDTO getByUsername(String username) throws ServiceException;

    UserDTO getByUsernamePasswordSalt(String username, String password) throws ServiceException;

    void insert(UserDTO user) throws ServiceException;
}
