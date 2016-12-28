package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.OrderProductDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.dto.UsersOrdersDTO;
import by.netcracker.shop.enums.UserRole;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.OrderProductService;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.services.UserService;
import by.netcracker.shop.utils.OrderProductConverter;
import by.netcracker.shop.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(Parameters.CONTROLLER_ORDER)
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private OrderProductConverter orderProductConverter;


    @RequestMapping(value = Parameters.REQUEST_ORDER_LIST, method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap){
        List<UsersOrdersDTO> orders= new ArrayList<>();
        try {
            orders = orderService.getOrdersByUsers();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_ORDERS, orders);
        return Parameters.TILES_ORDER_LIST;
    }

    @RequestMapping(value = "/show-order-for-{id}", method = RequestMethod.GET)
    public String showOrdersByUser(@PathVariable Long id, ModelMap modelMap) throws ServiceException {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String currentUser = ((UserDetails)principal).getUsername();
        UserDTO userDTO = null;
        userDTO = userService.getById(id);
        if(userDTO.getUsername()==currentUser||userDTO.getRole().equals(UserRole.ADMIN)){
                List<OrderProductDTO> orderProductByUser = orderProductService.getOrdersByUser(id);
                modelMap.addAttribute("userOrder", orderProductByUser);
                return "order/details";
            }
        else return "403";
    }

    @RequestMapping(value = "/add-{id}-to-order",method = RequestMethod.GET)
    public String addToOrder(@PathVariable Long id) throws ServiceException {

        return "redirect:/product/list";
    }

    @RequestMapping(value = "/remove-{id}-{username}",method = RequestMethod.GET)
    public String deleteProdFromOrder(@PathVariable Long id, @PathVariable String username, HttpServletRequest request) throws ServiceException{
        return "redirect:"+request.getHeader("Referer");
    }
}
