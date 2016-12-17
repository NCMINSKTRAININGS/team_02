package by.netcracker.shop.services;

import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface OrderService {

    void insert(OrderDto order) throws ServiceException;

    OrderDto getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<OrderDto> getAll() throws ServiceException;
}
