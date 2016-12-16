package by.netcracker.shop.controllers;

import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listOrders(ModelMap modelMap) {
        List<Product> products = service.findAll();
        modelMap.addAttribute("products", products);
        return "product/list";
    }
}
