package by.netcracker.shop.services;

import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface ProductService {
    Long insert(ProductDTO productDTO) throws ServiceException;

    ProductDTO getById(Long id) throws ServiceException;

    void update(ProductDTO productDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<ProductDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<ProductDTO> getByGap(int offset, int quantity) throws ServiceException;
}
