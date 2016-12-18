package by.netcracker.shop.services;

import by.netcracker.shop.dao.OrderDao;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.pojo.Order;
import by.netcracker.shop.services.OrderConverter;
import by.netcracker.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao dao;
    @Autowired
    private OrderConverter orderConverter;

    @Override
    public void save(OrderDto order) {
        if(order.getId()!=null){
            Order entity= dao.findById(order.getId());
            dao.save(orderConverter.converToLocal(order,entity));
        }else {
            dao.save(orderConverter.converToLocal(order, new Order()));
        }
    }

    @Override
    public OrderDto findById(Long id) {
        Order order= dao.findById(id);
        return orderConverter.convertToFront(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        dao.deleteOrderById(id);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders =dao.findAllOrders();
        List<OrderDto> result=new ArrayList<>(orders.size());
        for (Order order: orders) {
            result.add(orderConverter.convertToFront(order));
        }
        return result;
    }
}
