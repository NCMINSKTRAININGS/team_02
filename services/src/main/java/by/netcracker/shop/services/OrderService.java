package by.netcracker.shop.services;

import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface OrderService {

    void insert(OrderDto order) throws ServiceException;

    OrderDto getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<OrderDto> getAll() throws ServiceException;

    List<OrderDto> getOrdersByUser(UserDTO user) throws ServiceException;

    List<ProductDTO> getProductsByOrders(List<OrderDto> orderDtos) throws  ServiceException;

    List<Object[]> getGroupedOrders() throws  ServiceException;

    void addProdToOrder(UserDTO user, ProductDTO product) throws ServiceException, DAOException;

    void removeProdFromOrder(Long id, UserDTO user) throws ServiceException;
}
