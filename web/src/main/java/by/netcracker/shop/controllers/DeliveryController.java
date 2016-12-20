package by.netcracker.shop.controllers;

import by.netcracker.shop.dto.DeliveryDto;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.DeliveryService;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @Autowired
    UserService userService;

    @Autowired
    private MessageSource messageSource;

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

    @RequestMapping(value = { "/edit-{id}-delivery" }, method = RequestMethod.GET)
    public String editDelivery(@PathVariable Long id, ModelMap model) {
        try {
            DeliveryDto deliveryDto = deliveryService.getById(id);
            model.addAttribute("delivery", deliveryDto);
            model.addAttribute("edit", true);
        } catch (ServiceException e) {

        }
        return "delivery/new";
    }


    @RequestMapping(value = { "/edit-{id}-delivery" }, method = RequestMethod.POST)
    public String updateDelivery(@Valid @ModelAttribute("delivery") DeliveryDto deliveryDto, BindingResult result,
                             ModelMap model, @PathVariable Long id) {

        if (result.hasErrors()) {
            model.addAttribute("edit", true);
            return "delivery/new";
        }
        try {
            deliveryService.insert(deliveryDto);
        } catch (ServiceException e) {

        }
        return "redirect:/delivery/list";
    }

    @RequestMapping(value = { "/delete-{id}-delivery" }, method = RequestMethod.GET)
    public String deleteDelivery(@PathVariable Long id) {
        try {
            deliveryService.deleteById(id);
        } catch (ServiceException e) {

        }
        return "redirect:/delivery/list";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newDelivery(ModelMap model) {
        DeliveryDto delivery = new DeliveryDto();
        model.addAttribute("delivery", delivery);
        model.addAttribute("edit", false);
        return "delivery/new";
    }


    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveTeam(@Valid @ModelAttribute("delivery") DeliveryDto delivery, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "delivery/new";
        }


        try {
            deliveryService.insert(delivery);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/delivery/list";
    }
}
