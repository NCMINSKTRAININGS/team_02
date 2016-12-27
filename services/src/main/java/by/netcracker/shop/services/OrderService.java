package by.netcracker.shop.services;

import by.netcracker.shop.dto.OrderDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface OrderService {

    void insert(OrderDTO orderDTO) throws ServiceException;

    OrderDTO getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<OrderDTO> getAll() throws ServiceException;

}
