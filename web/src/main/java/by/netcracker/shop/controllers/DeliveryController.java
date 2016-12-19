package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.DeliveryDto;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.DeliveryService;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public String listDeliveries(ModelMap modelMap){
        List<DeliveryDto> deliveries= null;
        try {
            deliveries = deliveryService.getAll();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("deliveries",deliveries);
        return "delivery/list";
    }
}
