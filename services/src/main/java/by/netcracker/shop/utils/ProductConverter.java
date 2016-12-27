package by.netcracker.shop.utils;

import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductDTO> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Override
    public ProductDTO convertToFront(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCategoryId(product.getCategory().getId());
        dto.setManufacturerId(product.getManufacturer().getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setManufacturerName(product.getManufacturer().getName());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setKeywords(product.getKeywords());
        dto.setQuantityInStock(product.getQuantityInStock());
        return dto;
    }

    @Override
    public Product convertToLocal(ProductDTO productDTO, Product product) {
        try {
            product.setId(productDTO.getId());
            product.setCategory(categoryDAO.getById(productDTO.getCategoryId()));
            product.setManufacturer(manufacturerDAO.getById(productDTO.getManufacturerId()));
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setKeywords(productDTO.getKeywords());
            product.setQuantityInStock(productDTO.getQuantityInStock());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return product;
    }
}
