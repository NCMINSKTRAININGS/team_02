package by.netcracker.shop.services;

import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface ProductImageService {

    ProductImageDTO getById(Long id) throws ServiceException;

    void insert(ProductImageDTO productImageDTO) throws ServiceException;

    List<ProductImageDTO> getAll() throws ServiceException;

    void deleteById(Long id) throws ServiceException;
}
