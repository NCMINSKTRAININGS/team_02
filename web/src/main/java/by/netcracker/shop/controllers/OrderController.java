package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.services.UserService;
import by.netcracker.shop.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = Parameters.REQUEST_ORDER_LIST, method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap){
        List<Object[]> orders= null;
        try {
            orders = orderService.getGroupedOrders();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_ORDERS, orders);
        return Parameters.TILES_ORDER_LIST;
    }

    @RequestMapping(value = "/show-order-{id}", method = RequestMethod.GET)
    public String showOrders(@PathVariable Long id, ModelMap modelMap){
        List<OrderDto> dtoList = null;
        List<ProductDTO> productDTOS = null;
        UserDTO user;
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userName = ((UserDetails)principal).getUsername();
        try {
            user = userConverter.convertToFront(userService.getById(id));
            dtoList = orderService.getOrdersByUser(user);
            productDTOS = orderService.getProductsByOrders(dtoList);
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("productList", productDTOS);
        modelMap.addAttribute("username", userName);
        return "order/details";
    }

    @RequestMapping(value = "/add-{id}-to-order",method = RequestMethod.GET)
    public String addToOrder(@PathVariable Long id) throws ServiceException {
        ProductDTO product=productService.getById(id);
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userName = ((UserDetails)principal).getUsername();

        if (product.getQuantityInStock() == 0){
            return "redirect:/product/list";
        }else {
            try {
                orderService.addProdToOrder(userService.getByUsername(userName),product);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/product/list";
    }

    @RequestMapping(value = "/remove-{id}-{username}",method = RequestMethod.GET)
    public String deleteProdFromOrder(@PathVariable Long id, @PathVariable String username, HttpServletRequest request) throws ServiceException{
            orderService.removeProdFromOrder(id,userService.getByUsername(username));
        return "redirect:"+request.getHeader("Referer");
    }
}
