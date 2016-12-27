package by.netcracker.shop.services;


import by.netcracker.shop.dto.DeliveryDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface DeliveryService  {
    void insert(DeliveryDTO deliveryDTO) throws ServiceException;

    DeliveryDTO getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<DeliveryDTO> getAll() throws ServiceException;
}
