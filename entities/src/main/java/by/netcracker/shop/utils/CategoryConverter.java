package by.netcracker.shop.utils;

import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.pojo.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryDTO> {

    @Override
    public CategoryDTO convertToFront(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setImage(category.getImage());
        return dto;
    }

    @Override
    public Category convertToLocal(CategoryDTO categoryDTO, Category category) {
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImage(categoryDTO.getImage());
        return category;
    }
}
