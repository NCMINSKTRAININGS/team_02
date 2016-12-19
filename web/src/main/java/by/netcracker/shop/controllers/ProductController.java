package by.netcracker.shop.controllers;

import by.netcracker.shop.exceptions.ServiceException;
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
    public String listOrders(ModelMap modelMap) {
        List<Product> products = null;
        try {
            products = service.getAll();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute("products", products);
        return "product/list";
    }

    @RequestMapping(value = {"/createproduct"}, method = RequestMethod.GET)
    public String createProduct(ModelMap modelMap) {
        Product product = new Product();
        modelMap.addAttribute("product", product);
        return "product/new";
    }

    @RequestMapping(value = {"/createproduct"}, method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "product/new";
        } //TODO: validation
        try {
            service.insert(product);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/product/list";
    }

    @RequestMapping(value = {"/update-product-{id}"}, method = RequestMethod.GET)
    public String editProduct(@PathVariable Long id, ModelMap modelMap) {
        Product product = null;
        try {
            product = service.getById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("product", product);
        return "product/edit";
    }

    @RequestMapping(value = {"/update-product-{id}"}, method = RequestMethod.POST)
    public String updateProduct(@Valid Product product, BindingResult bindingResult, @PathVariable Long id, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "product/edit";
        }
        try {
            service.updateEntity(product);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/product/list";
    }

    @RequestMapping(value = {"/delete-product-{id}"}, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/product/list";
    }

/*    @ModelAttribute("categories")
    public List<Category> getCategories() {
        try {
            return categoryService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @ModelAttribute("manufacturers")
    public List<Manufacturer> getManufacturers() {
        try {
            return manufacturerService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }*/
}
