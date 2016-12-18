package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;

    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap){
        List<OrderDto> orders= null;
        try {
            orders = service.getAll();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("orders",orders);
        return "order/list";
    }

    @RequestMapping(value = {"/show-{id}-order"}, method = RequestMethod.GET)
    public String showOrder(@PathVariable Long id, ModelMap modelMap){
        OrderDto orderDto= null;
        try {
            orderDto = service.getById(id);
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("order",orderDto);
        return "order/details";
    }
}
