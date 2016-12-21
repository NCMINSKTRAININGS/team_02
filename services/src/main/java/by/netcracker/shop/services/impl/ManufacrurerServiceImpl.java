package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Manufacturer;
import by.netcracker.shop.services.ManufacturerService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dmitry
 */
@Service("manufacturerService")
@Transactional
public class ManufacrurerServiceImpl implements ManufacturerService{
    @Autowired
    private ManufacturerDAO dao;

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public Long insert(Manufacturer manufacturer) throws ServiceException {
        Long id;
        try {
            id = dao.insert(manufacturer);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
        return id;
    }

    @Override
    public Manufacturer getById(Long id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void update(Manufacturer manufacturer) throws ServiceException {
        try {
            dao.update(manufacturer);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Manufacturer> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DAOException ex) {
            logger.error(ServiceConstants.ERROR_SERVICE, ex);
            throw new ServiceException(ex);
        }
    }

}
