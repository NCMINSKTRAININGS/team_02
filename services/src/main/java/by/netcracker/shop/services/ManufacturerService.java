package by.netcracker.shop.services;

import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface ManufacturerService {
    Long insert(ManufacturerDTO manufacturerDTO) throws ServiceException;

    ManufacturerDTO getById(Long id) throws ServiceException;

    void update(ManufacturerDTO manufacturerDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<ManufacturerDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<ManufacturerDTO> getByGap(int offset, int quantity) throws ServiceException;
}
