package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductImageConverter {
    public ProductImageDTO toImageDTO(ProductImage image) {
        ProductImageDTO dto = new ProductImageDTO();
        dto.setId(image.getId());
        dto.setProduct(image.getProduct());
        dto.setImage(image.getImage());
        return dto;
    }

    public ProductImage toImagePOJO(ProductImageDTO productImageDTO, ProductImage image) {
        image.setId(productImageDTO.getId());
        image.setProduct(productImageDTO.getProduct());
        image.setImage(productImageDTO.getImage());
        return image;
    }
}
