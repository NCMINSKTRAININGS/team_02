package by.netcracker.shop.services;


import by.netcracker.shop.dto.DeliveryDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface DeliveryService  {
    Long insert(DeliveryDTO deliveryDTO) throws ServiceException;

    DeliveryDTO getById(Long id) throws ServiceException;

    void update(DeliveryDTO categoryDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<DeliveryDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<DeliveryDTO> getByGap(int offset, int quantity) throws ServiceException;
}
