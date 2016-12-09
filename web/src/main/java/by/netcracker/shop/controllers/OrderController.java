package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by j on 8.12.16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;

    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap){
        List<OrderDto> orders= service.findAllOrders();
        modelMap.addAttribute("orders",orders);
        return "order/list";
    }
}
