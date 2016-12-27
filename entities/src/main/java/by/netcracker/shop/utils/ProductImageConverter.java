package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductImageConverter {
    public ProductImageDTO toImageDTO(ProductImage image) {
        ProductImageDTO imageDTO;
        if (image == null)
            return null;
        imageDTO = new ProductImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setProduct(image.getProduct());
        imageDTO.setImage(image.getImage());
        return imageDTO;
    }

    public ProductImage toImagePOJO(ProductImageDTO imageDTO, ProductImage image) {
        if (imageDTO == null || image == null)
            return null;
        image.setId(imageDTO.getId());
        image.setProduct(imageDTO.getProduct());
        image.setImage(imageDTO.getImage());
        return image;
    }
}
