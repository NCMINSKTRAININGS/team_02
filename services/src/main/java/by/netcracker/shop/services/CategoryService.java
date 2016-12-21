package by.netcracker.shop.services;

import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface CategoryService {

    CategoryDTO getById(Long id) throws ServiceException;

    void insert(CategoryDTO categoryDTO) throws ServiceException;

    List<CategoryDTO> getAll() throws ServiceException;

    void deleteById(Long id) throws ServiceException;
}
