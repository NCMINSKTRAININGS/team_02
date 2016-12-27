package by.netcracker.shop.utils;

import by.netcracker.shop.dao.CategoryDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter implements Converter<Product, ProductDTO> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Autowired
    private ProductImageDAO imageDAO;

    @Override
    public ProductDTO convertToFront(Product product) {
        ProductDTO dto = new ProductDTO();
        List<String> list = new ArrayList<>();
        try {
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
            for (ProductImage image : imageDAO.getImagesByProduct(product))
            {
                list.add(image.getImage());
            }
            if (list.iterator().hasNext()) {
                dto.setImage(list.iterator().next());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
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
