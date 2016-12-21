package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.OrderDto;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.User;
import by.netcracker.shop.services.OrderService;
import by.netcracker.shop.services.ProductService;
import by.netcracker.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(Parameters.CONTROLLER_ORDER)
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

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
        try {
            user= userService.getById(id);
            dtoList = orderService.getOrdersByUser(user);
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_ORDERS, dtoList);
        return Parameters.TILES_ORDER_DETAILS;
    }

    @RequestMapping(value = "/add-{id}-to-order",method = RequestMethod.GET)
    public String addToOrder(@PathVariable Long id, HttpSession session) throws ServiceException {
        Product product=productService.getById(id);
        if (product.getQuantityInStock() == 0){
            return "redirect:/product/list";
        }else {
            User user;
            user = (User)session.getAttribute(Parameters.ENTITY_USER);

            List<OrderDto> orders = orderService.getOrdersByUser(user);
            OrderDto order;
            order = orders.get(orders.size()-1);
            if(!order.getProducts().contains(product)){
                order.getProducts().add(product);
                order.setPrice(order.getPrice()+product.getPrice());
            }else {
                Set<Product> productSet= new HashSet<>();
                productSet.add(product);
                OrderDto newOrder = new OrderDto();
                newOrder.setUser(user);
                newOrder.setComment("");
                newOrder.setPrice(product.getPrice());
                newOrder.setProducts(productSet);
                orderService.insert(newOrder);
            }

            orderService.insert(order);

            product.setQuantityInStock(product.getQuantityInStock()-1);
            productService.update(product);
        }
        return "redirect:/product/list";
    }
}
