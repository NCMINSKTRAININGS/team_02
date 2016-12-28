package by.netcracker.shop.services;


import by.netcracker.shop.dto.OrderProductDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.OrderProductId;

import java.util.List;

public interface OrderProductService {

    OrderProductDTO getById(OrderProductId id) throws ServiceException;

    void insert(OrderProductDTO productDTO) throws ServiceException;

    List<OrderProductDTO> getAll() throws ServiceException;

    void deleteProductFromOrder(Long orderId, Long productId) throws ServiceException;

    List<OrderProductDTO> getOrdersByUser(Long userId) throws ServiceException;
}
