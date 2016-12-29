package by.netcracker.shop.controllers;

import by.netcracker.shop.constants.Parameters;
import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.services.CategoryService;
import by.netcracker.shop.services.ManufacturerService;
import by.netcracker.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Parameters.CONTROLLER_PRODUCT)
public class ProductController {
    @Autowired
    ProductService service;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_LIST, method = RequestMethod.GET)
    public String listProducts(ModelMap modelMap, @RequestParam(value = "page", required = false) String page) {
        int pageNumber;
        int recordsPerPage = 2;
        int numberOfPages;
        int numberOfRecords;
        List<ProductDTO> products = null;

        if (page != null){
            try {
                pageNumber = Integer.parseInt(page);
            } catch (NumberFormatException e){
                pageNumber = 1;
            }
        } else {
            pageNumber = 1;
        }
        try {
            products = service.getByGap((pageNumber - 1) * recordsPerPage, recordsPerPage);
            numberOfRecords = Math.toIntExact(service.getCount());
        } catch (ServiceException e) {
            //todo
            if (products != null)
                numberOfRecords = products.size();
            else numberOfRecords = recordsPerPage;
        }
        numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        modelMap.addAttribute("currentPage", pageNumber);
        modelMap.addAttribute("numberOfPages", numberOfPages);
        modelMap.addAttribute(Parameters.FIELD_PRODUCTS, products);
        return Parameters.TILES_PRODUCT_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_CREATE, method = RequestMethod.GET)
    public String createProduct(ModelMap modelMap) {
        ProductDTO dto = new ProductDTO();
        modelMap.addAttribute(Parameters.FIELD_PRODUCT_DTO, dto);
        modelMap.addAttribute(Parameters.EDIT, false);
        return Parameters.TILES_PRODUCT_NEW;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_CREATE, method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute(Parameters.FIELD_PRODUCT_DTO) ProductDTO dto,
                              BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return Parameters.TILES_PRODUCT_NEW;
        }
        try {
            service.insert(dto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:" + Parameters.CONTROLLER_PRODUCT + Parameters.REQUEST_PRODUCT_LIST;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_EDIT, method = RequestMethod.GET)
    public String editProduct(@PathVariable Long id, ModelMap modelMap) {
        ProductDTO dto = null;
        try {
            dto = service.getById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute(Parameters.FIELD_PRODUCT_DTO, dto);
        modelMap.addAttribute(Parameters.EDIT, true);
        return Parameters.TILES_PRODUCT_NEW;
    }

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_EDIT, method = RequestMethod.POST)
    public String updateProduct(@Valid @ModelAttribute(Parameters.FIELD_PRODUCT_DTO) ProductDTO dto,
                                BindingResult bindingResult, @PathVariable Long id, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute(Parameters.EDIT, true);
            return Parameters.TILES_PRODUCT_NEW;
        }
        try {
            service.insert(dto);
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

    @RequestMapping(value = Parameters.REQUEST_PRODUCT_SHOW, method = RequestMethod.GET)
    public String showProduct(@PathVariable Long id, ModelMap modelMap) {
        ProductDTO dto = null;
        try {
            dto = service.getById(id);
            if (service.getById(id).getQuantityInStock() != 0) {
                modelMap.addAttribute(Parameters.AVAILABLE, true);
            } else {
                modelMap.addAttribute(Parameters.AVAILABLE, false);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute(Parameters.FIELD_PRODUCT_DTO, dto);
        return Parameters.TILES_PRODUCT_DETAILS;
    }

    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> list = null;
        try {
            list = categoryService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return list;
    }

    @ModelAttribute("manufacturers")
    public List<ManufacturerDTO> getManufacturers() {
        List<ManufacturerDTO> list = null;
        try {
            list = manufacturerService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return list;
    }
}
