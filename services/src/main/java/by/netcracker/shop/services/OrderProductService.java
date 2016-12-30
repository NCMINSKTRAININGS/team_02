package by.netcracker.shop.services;


import by.netcracker.shop.dto.OrderProductDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.OrderProductId;

import java.util.List;
import java.util.Map;

public interface OrderProductService {

    OrderProductDTO getById(OrderProductId id) throws ServiceException;

    void insert(OrderProductDTO productDTO) throws ServiceException;

    List<OrderProductDTO> getAll() throws ServiceException;

    void deleteProductFromOrder(Long orderId, Long productId) throws ServiceException;

    List<OrderProductDTO> getOrdersByUser(Long userId) throws ServiceException;

    List<OrderProductDTO> getOrderByOrderId(Long orderId) throws ServiceException;

    Map<Long,List<OrderProductDTO>> separateByOrderId(List<OrderProductDTO> orderProductDTOs) throws ServiceException;

    Double getOrderPrice (Long orderId) throws ServiceException;
}
