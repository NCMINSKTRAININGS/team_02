package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.OrderProduct;
import by.netcracker.shop.pojo.OrderProductId;

import java.util.List;

public interface OrderProductDAO  {

    OrderProductId insert(OrderProduct entity) throws DAOException ;

    OrderProduct getById(OrderProductId id) throws DAOException ;

    void deleteById(OrderProductId id) throws DAOException ;

    List<OrderProduct> getAll() throws DAOException ;

    Long getCount() throws DAOException;

    List<OrderProduct> getByGap(int offset, int quantity) throws DAOException ;

    List<OrderProduct> getByUserId(Long userId);

    List<OrderProduct> getByOrderId(Long orderId);

    Double getOrderPrice(Long id)throws DAOException;
}
