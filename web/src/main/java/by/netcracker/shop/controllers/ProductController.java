package by.netcracker.shop.controllers;

import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listProducts(ModelMap modelMap) {
        List<Product> products = service.findAll();
        modelMap.addAttribute("products", products);
        return "product/list";
    }

    @RequestMapping(value = {"/createproduct"}, method = RequestMethod.GET)
    public String createProduct(ModelMap modelMap) {
        Product product = new Product();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("edit", false);
        return "product/newproduct";
    }

    @RequestMapping(value = {"/createproduct"}, method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "product/newproduct";
        } //TODO: validation
        service.save(product);
        return "redirect:/product/list";
    }

    @RequestMapping(value = {"/update-product-{id}"}, method = RequestMethod.GET)
    public String editProduct(@PathVariable Integer id, ModelMap modelMap) {
        Product product = service.finById(id);
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("edit", true);
        return "product/newproduct";
    }

    @RequestMapping(value = {"/update-product-{id}"}, method = RequestMethod.POST)
    public String updateProduct(@Valid Product product, BindingResult bindingResult, @PathVariable Integer id, ModelMap modelMap) {
        if (bindingResult.hasErrors()){
            return "product/newproduct";
        }
        service.update(product);
        return "redirect:/product/list";
    }

    @RequestMapping(value = {"/delete-product-{id}"}, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/product/list";
    }
}
