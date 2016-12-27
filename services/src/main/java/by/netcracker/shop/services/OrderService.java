package by.netcracker.shop.services;

import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface OrderService {

    void insert(OrderDTO orderDTO) throws ServiceException;

    OrderDTO getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<OrderDTO> getAll() throws ServiceException;

    List<OrderDto> getOrdersByUser(UserDTO user) throws ServiceException;

    void addProdToOrder(UserDTO user, ProductDTO product) throws ServiceException, DAOException;

    void removeProdFromOrder(Long id, UserDTO user) throws ServiceException;
}
