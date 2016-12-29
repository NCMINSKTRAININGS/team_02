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
import java.util.*;

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
        String username = ((UserDetails)principal).getUsername();
        UserDTO currentUser = userService.getByUsername(username);
        UserDTO userDTO = userService.getById(id);

        if(Objects.equals(userDTO.getUsername(), currentUser.getUsername())||currentUser.getRole().equals(UserRole.ADMIN)){
            Map<Long,List<OrderProductDTO>> map = orderProductService.separateByOrderId(orderProductService.getOrdersByUser(id));
            Map<Long,List<OrderProductDTO>> treeMap = new TreeMap<>(
                    (Comparator<Long>) (o1, o2) -> o2.compareTo(o1));
            treeMap.putAll(map);
            modelMap.addAttribute("userOrder", treeMap);

            modelMap.addAttribute("signedIn",currentUser);
            return "order/details";
            }
        else return "403";
    }

    @RequestMapping(value = "/add-{prodId}-to-order",method = RequestMethod.GET)
    public String addToOrder(@PathVariable Long prodId,  HttpServletRequest request) throws ServiceException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        orderService.addToOrder(userService.getByUsername(username),prodId);
        return "redirect:"+request.getHeader("Referer");
    }

    @RequestMapping(value = "/remove-{orderId}-{productId}",method = RequestMethod.GET)
    public String deleteProdFromOrder(@PathVariable Long orderId, @PathVariable Long productId, HttpServletRequest request) throws ServiceException{
        orderProductService.deleteProductFromOrder(orderId,productId);
        return "redirect:"+request.getHeader("Referer");
    }

    @RequestMapping(value = "/order-{orderId}",method = RequestMethod.GET)
    public String editOrder(@PathVariable Long orderId, ModelMap model) throws ServiceException {
        Map<Long,List<OrderProductDTO>> map = orderProductService.separateByOrderId(orderProductService.getOrderByOrderId(orderId));

        model.addAttribute("orderMap", map);
        return "order/order";
    }
}
