package by.netcracker.shop.services;

import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface UserService {
    Long insert(UserDTO user) throws ServiceException;

    UserDTO getById(Long id) throws ServiceException;

    void update(UserDTO user) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<UserDTO> getAll() throws ServiceException;

    UserDTO getByUsername(String username) throws ServiceException;

    UserDTO getByUsernamePassword(String username, String password) throws ServiceException;

    Long getCount() throws ServiceException;

    List<UserDTO> getByGap(int offset, int quantity) throws ServiceException;
}
