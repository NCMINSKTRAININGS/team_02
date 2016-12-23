package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.pojo.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductDTO> {

    @Override
    public ProductDTO convertToFront(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory());
        dto.setManufacturer(product.getManufacturer());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setKeywords(product.getKeywords());
        dto.setQuantityInStock(product.getQuantityInStock());
        return dto;
    }

    @Override
    public Product convertToLocal(ProductDTO productDTO, Product product) {
        product.setId(productDTO.getId());
        product.setCategory(productDTO.getCategory());
        product.setManufacturer(productDTO.getManufacturer());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setKeywords(productDTO.getKeywords());
        product.setQuantityInStock(productDTO.getQuantityInStock());
        return product;
    }
}
