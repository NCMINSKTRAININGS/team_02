package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.dto.UserDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.services.UserService;
import by.netcracker.shop.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(Parameters.CONTROLLER_ORDER)
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = Parameters.REQUEST_ORDER_LIST, method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap){
        List orders= null;
        try {
            orders = orderService.getGroupedOrders();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_ORDERS, orders);
        return Parameters.TILES_ORDER_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_ORDER_SHOW, method = RequestMethod.GET)
    public String showOrder(@PathVariable Long id, ModelMap modelMap){
        List<OrderDto> dtoList = null;
        User user;
        UserDTO userDTO;//todo fixed
        try {
            userDTO = userService.getById(id);
            user = userConverter.convertToLocal(userDTO, new User());
            dtoList = orderService.getOrdersByUser(user);
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_ORDERS, dtoList);
        return Parameters.TILES_ORDER_DETAILS;
    }

    @RequestMapping(value = "/add-{id}-to-order",method = RequestMethod.GET)
    public String addToOrder(@PathVariable Long id, HttpSession session) throws ServiceException {
        ProductDTO product=productService.getById(id);
        User user;
        user = (User)session.getAttribute(Parameters.ENTITY_USER);

        if (product.getQuantityInStock() == 0){
            return "redirect:/product/list";
        }else {
            try {
                orderService.addProdToOrder(user,product);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/product/list";
    }
}
