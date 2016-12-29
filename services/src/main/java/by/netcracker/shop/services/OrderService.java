package by.netcracker.shop.services;

import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;

import java.util.List;

public interface OrderService {
    Long insert(OrderDTO orderDTO) throws ServiceException;

    OrderDTO getById(Long id) throws ServiceException;

    void update(OrderDTO orderDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<OrderDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<OrderDTO> getByGap(int offset, int quantity) throws ServiceException;

    List<OrderDTO> getOrdersByUser(User userPOJO) throws ServiceException;

    List<Object[]> getGroupedOrders() throws  ServiceException;

    void addProdToOrder(User userPOJO, ProductDTO productDTO) throws ServiceException;
}
