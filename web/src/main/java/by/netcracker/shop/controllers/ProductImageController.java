package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.ProductImage;
import by.netcracker.shop.services.ProductImageService;
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
@RequestMapping(Parameters.CONTROLLER_PRODUCT_IMAGE)
public class ProductImageController {

    @Autowired
    ProductImageService service;

    @Autowired
    ProductService productService;

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_IMAGE_LIST, method = RequestMethod.GET)
    public String listImages(ModelMap modelMap) {
        List<ProductImageDTO> images = null;
        try {
            images = service.getAll();
        } catch (ServiceException e) {
            //todo
        }
        modelMap.addAttribute(Parameters.FIELD_PRODUCT_IMAGES, images);
        return Parameters.TILES_PRODUCT_IMAGE_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_IMAGE_CREATE, method = RequestMethod.GET)
    public String createImage(ModelMap modelMap) {
        ProductImage image = new ProductImage();
        modelMap.addAttribute(Parameters.FIELD_PRODUCT_IMAGE, image);
        modelMap.addAttribute(Parameters.EDIT, false);
        return Parameters.TILES_PRODUCT_IMAGE_NEW;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_IMAGE_CREATE, method = RequestMethod.POST)
    public String saveImage(@Valid @ModelAttribute(Parameters.FIELD_PRODUCT_IMAGE) ProductImageDTO image,
                            BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return Parameters.TILES_PRODUCT_IMAGE_NEW;
        }
        try {
            service.insert(image);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT_IMAGE + Parameters.REQUEST_PRODUCT_IMAGE_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_IMAGE_EDIT, method = RequestMethod.GET)
    public String editImage(@PathVariable Long id, ModelMap modelMap) {
        ProductImageDTO image = null;
        try {
            image = service.getById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute(Parameters.FIELD_PRODUCT_IMAGE, image);
        modelMap.addAttribute(Parameters.EDIT, true);
        return Parameters.TILES_PRODUCT_IMAGE_NEW;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_IMAGE_EDIT, method = RequestMethod.POST)
    public String updateImage(@Valid @ModelAttribute(Parameters.FIELD_PRODUCT_IMAGE) ProductImageDTO image,
                                BindingResult bindingResult, @PathVariable Long id, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute(Parameters.EDIT, true);
            return Parameters.TILES_PRODUCT_IMAGE_NEW;
        }
        try {
            service.insert(image);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT_IMAGE + Parameters.REQUEST_PRODUCT_IMAGE_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_IMAGE_DELETE, method = RequestMethod.GET)
    public String deleteImage(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT_IMAGE + Parameters.REQUEST_PRODUCT_IMAGE_LIST;
    }

    @ModelAttribute("products")
    public List<ProductDTO> getProducts() {
        List<ProductDTO> list = null;
        try {
            list = productService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return list;
    }
}
