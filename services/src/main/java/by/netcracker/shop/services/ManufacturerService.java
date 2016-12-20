package by.netcracker.shop.services;

import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;

public interface ManufacturerService {

    ManufacturerDTO getById(Long id) throws ServiceException;

    void insert(ManufacturerDTO manufacturerDTO) throws ServiceException;

    List<ManufacturerDTO> getAll() throws ServiceException;

    void deleteById(Long id) throws ServiceException;
}
