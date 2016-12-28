package by.netcracker.shop.utils;

import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.pojo.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO;
        if (category == null)
            return null;
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setImage(category.getImage());
        return categoryDTO;
    }

    public Category toCategoryPOJO(CategoryDTO categoryDTO, Category category) {
        if (categoryDTO == null || category == null)
            return null;
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImage(categoryDTO.getImage());
        return category;
    }
}
