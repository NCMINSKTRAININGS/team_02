package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO;
        if (product == null)
            return null;
        productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setManufacturerId(product.getManufacturer().getId());
        productDTO.setCategoryName(product.getCategory().getName());
        productDTO.setManufacturerName(product.getManufacturer().getName());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setKeywords(product.getKeywords());
        productDTO.setQuantityInStock(product.getQuantityInStock());
        productDTO.setImage(image.getImage());
        return productDTO;
    }

    public Product toProductPOJO(ProductDTO productDTO, Product product,
                                 Category category, Manufacturer manufacturer) {
        if (productDTO == null || product == null)
            return null;
        product.setId(productDTO.getId());
        product.setCategory(category);
        product.setManufacturer(manufacturer);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setKeywords(productDTO.getKeywords());
        product.setQuantityInStock(productDTO.getQuantityInStock());
        return product;
    }
}
