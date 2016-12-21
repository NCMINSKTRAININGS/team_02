package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductImageConverter implements Converter<ProductImage, ProductImageDTO> {

    @Override
    public ProductImageDTO convertToFront(ProductImage image) {
        ProductImageDTO dto = new ProductImageDTO();
        dto.setId(image.getId());
        dto.setProduct(image.getProduct());
        dto.setImage(image.getImage());
        return dto;
    }

    @Override
    public ProductImage convertToLocal(ProductImageDTO productImageDTO, ProductImage image) {
        image.setId(productImageDTO.getId());
        image.setProduct(productImageDTO.getProduct());
        image.setImage(productImageDTO.getImage());
        return image;
    }
}
