package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.pojo.Category;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO toProductDTO(Product product, ProductImage image) {
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
        dto.setImage(image.getImage());
        return dto;
    }

    public Product toProductPOJO(ProductDTO productDTO, Product product,
                                 Category category, Manufacturer manufacturer) {
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
