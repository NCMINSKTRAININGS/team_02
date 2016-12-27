package by.netcracker.shop.utils;

import by.netcracker.shop.dto.OrderProductDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.*;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProductConverter {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConverter productConverter;

    public OrderProductDTO convertToFront(OrderProduct orderProduct) {
        OrderProductDTO dto = new OrderProductDTO();

        dto.setProductQuantityInOrder(orderProduct.getPruductQuantity());

        dto.setOrderId(orderProduct.getOrder().getId());
        dto.setUserId(orderProduct.getOrder().getUser().getId());
        dto.setUsername(orderProduct.getOrder().getUser().getUsername());
        if(orderProduct.getOrder().getPayment()!=null){
            dto.setPaymentId(orderProduct.getOrder().getPayment().getId());
        }else dto.setPaymentId(null);
        if(orderProduct.getOrder().getDelivery()!=null){
            dto.setDeliveryId(orderProduct.getOrder().getDelivery().getId());
        }else dto.setDeliveryId(null);
        dto.setOrderPrice(orderProduct.getOrder().getPrice());
        dto.setProduced(orderProduct.getOrder().getProduced());
        dto.setOrderComment(orderProduct.getOrder().getComment());

        dto.setProductId(orderProduct.getProduct().getId());
        dto.setProductName(orderProduct.getProduct().getName());
        dto.setProductDescription(orderProduct.getProduct().getDescription());
        dto.setKeywords(orderProduct.getProduct().getKeywords());
        dto.setProductPrice(orderProduct.getProduct().getPrice());
        dto.setQuantityInStock(orderProduct.getProduct().getQuantityInStock());

        return dto;
    }

    public OrderProduct convertToLocal(OrderProductDTO orderProductDTO, OrderProduct orderProduct) throws ServiceException {
        orderProduct.setOrder(orderConverter.convertToLocal(orderService.getById(orderProductDTO.getOrderId()),orderProduct.getOrder()));
        orderProduct.setProduct(productConverter.convertToLocal(productService.getById(orderProductDTO.getProductId()),orderProduct.getProduct()));
        orderProduct.setPruductQuantity(orderProductDTO.getProductQuantityInOrder());
        orderProduct.setPrice(orderProductDTO.getProductPrice());
        return orderProduct;
    }
}
