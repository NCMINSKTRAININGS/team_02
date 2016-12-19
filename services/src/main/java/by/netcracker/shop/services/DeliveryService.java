package by.netcracker.shop.services;


import by.netcracker.shop.dto.DeliveryDto;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface DeliveryService  {
    void insert(DeliveryDto delivery) throws ServiceException;

    DeliveryDto getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<DeliveryDto> getAll() throws ServiceException;
}
