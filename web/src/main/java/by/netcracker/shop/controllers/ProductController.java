package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
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
@RequestMapping(Parameters.CONTROLLER_PRODUCT)
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_LIST, method = RequestMethod.GET)
    public String listProducts(ModelMap modelMap) {
        List<Product> products = null;
        try {
            products = service.getAll();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_PRODUCTS, products);
        return Parameters.TILES_PRODUCT_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_CREATE, method = RequestMethod.GET)
    public String createProduct(ModelMap modelMap) {
        Product product = new Product();
        modelMap.addAttribute(Parameters.FIELD_PRODUCT, product);
        return Parameters.TILES_PRODUCT_NEW;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_CREATE, method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute(Parameters.FIELD_PRODUCT) @Valid Product product,
                              BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return Parameters.TILES_PRODUCT_NEW;
        } //TODO: validation
        try {
            service.insert(product);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT + Parameters.REQUEST_PRODUCT_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_EDIT, method = RequestMethod.GET)
    public String editProduct(@PathVariable Long id, ModelMap modelMap) {
        Product product = null;
        try {
            product = service.getById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute(Parameters.FIELD_PRODUCT, product);
        return Parameters.TILES_PRODUCT_EDIT;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_EDIT, method = RequestMethod.POST)
    public String updateProduct(@Valid Product product, BindingResult bindingResult, @PathVariable Long id,
                                ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return Parameters.TILES_PRODUCT_EDIT;
        }
        try {
            service.updateEntity(product);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT + Parameters.REQUEST_PRODUCT_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_DELETE, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT + Parameters.REQUEST_PRODUCT_LIST;
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
