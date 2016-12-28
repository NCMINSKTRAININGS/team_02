package by.netcracker.shop.services;

import by.netcracker.shop.dto.CategoryDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface CategoryService {
    void insert(CategoryDTO categoryDTO) throws ServiceException;

    CategoryDTO getById(Long id) throws ServiceException;

    void update(CategoryDTO categoryDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<CategoryDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<CategoryDTO> getByGap(int offset, int quantity) throws ServiceException;
}
