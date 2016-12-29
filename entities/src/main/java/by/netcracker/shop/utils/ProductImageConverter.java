package by.netcracker.shop.utils;

import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.pojo.Product;
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
        imageDTO.setProductId(image.getProduct().getId());
        imageDTO.setProductName(image.getProduct().getName());
        imageDTO.setImage(image.getImage());
        return imageDTO;
    }

    public ProductImage toImagePOJO(ProductImageDTO imageDTO, ProductImage image, Product product) {
        if (imageDTO == null || image == null)
            return null;
        image.setId(imageDTO.getId());
        image.setProduct(product);
        image.setImage(imageDTO.getImage());
        return image;
    }
}
