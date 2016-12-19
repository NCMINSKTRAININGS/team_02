package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.UserService;
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
    OrderService orderService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap){
        List orders= null;
        try {
            orders = orderService.getGroupedOrders();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("orders",orders);
        return "order/list";
    }

    @RequestMapping(value = {"/show-order-{id}"}, method = RequestMethod.GET)
    public String showOrder(@PathVariable Long id, ModelMap modelMap){
        List<OrderDto> dtoList = null;
        User user;
        try {
            user= userService.getById(id);
            dtoList = orderService.getOrdersByUser(user);
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("orders",dtoList);
        return "order/details";
    }
}
