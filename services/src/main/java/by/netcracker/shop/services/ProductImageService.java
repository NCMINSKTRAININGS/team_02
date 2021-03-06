package by.netcracker.shop.services;

import by.netcracker.shop.dto.ProductImageDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface ProductImageService {
    Long insert(ProductImageDTO imageDTO) throws ServiceException;

    ProductImageDTO getById(Long id) throws ServiceException;

    void update(ProductImageDTO imageDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<ProductImageDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<ProductImageDTO> getByGap(int offset, int quantity) throws ServiceException;
}
