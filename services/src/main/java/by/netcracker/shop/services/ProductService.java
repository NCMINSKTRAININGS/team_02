package by.netcracker.shop.services;

import by.netcracker.shop.dto.ProductDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface ProductService {

    ProductDTO getById(Long id) throws ServiceException;

    void insert(ProductDTO productDTO) throws ServiceException;

    List<ProductDTO> getAll() throws ServiceException;

    void deleteById(Long id) throws ServiceException;
}
