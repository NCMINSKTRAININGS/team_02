package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.dto.ManufacturerDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.services.ManufacturerService;
import by.netcracker.shop.utils.ManufacturerConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("manufacturerService")
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerDAO dao;

    @Autowired
    private ManufacturerConverter converter;

    private static Logger logger = Logger.getLogger(ManufacturerServiceImpl.class);

    @Override
    public ManufacturerDTO getById(Long id) throws ServiceException {
        Manufacturer manufacturerPOJO;
        try {
            manufacturerPOJO = dao.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.toManufacturerDTO(manufacturerPOJO);
    }

    @Override
    public void insert(ManufacturerDTO manufacturerDTO) throws ServiceException {
        Manufacturer manufacturerPOJO = null;
        try {
            if (manufacturerDTO.getId() != null) {
                manufacturerPOJO = dao.getById(manufacturerDTO.getId());
            }
            if (manufacturerPOJO == null)
                manufacturerPOJO = new Manufacturer();
            dao.insert(converter.toManufacturerPOJO(manufacturerDTO, manufacturerPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ManufacturerDTO> getAll() throws ServiceException {
        List<Manufacturer> manufacturerPOJOs;
        List<ManufacturerDTO> manufacturerDTOs;
        try {
            manufacturerPOJOs = dao.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        manufacturerDTOs = new ArrayList<>(manufacturerPOJOs.size());
        for (Manufacturer manufacturer: manufacturerPOJOs){
            manufacturerDTOs.add(converter.toManufacturerDTO(manufacturer));
        }
        return manufacturerDTOs;
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }
}
