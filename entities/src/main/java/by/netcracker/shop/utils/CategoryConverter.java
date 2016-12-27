package by.netcracker.shop.utils;

import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.pojo.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setImage(category.getImage());
        return dto;
    }

    public Category toCategoryPOJO(CategoryDTO categoryDTO, Category category) {
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImage(categoryDTO.getImage());
        return category;
    }
}
