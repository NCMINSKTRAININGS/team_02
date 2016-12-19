package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.DeliveryDAO;
import by.netcracker.shop.dto.DeliveryDto;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Delivery;
import by.netcracker.shop.services.DeliveryService;
import by.netcracker.shop.utils.DeliveryConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service("deliveryService")
@Transactional
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryDAO deliveryDAO;

    @Autowired
    private DeliveryConverter converter;

    private static Logger logger = Logger.getLogger(DeliveryServiceImpl.class);

    @Override
    public void insert(DeliveryDto delivery) throws ServiceException {
        try {
            if (delivery.getId() != null) {
                Delivery entity = deliveryDAO.getById(delivery.getId());
                deliveryDAO.insert(converter.converToLocal(delivery, entity));
            } else {
                deliveryDAO.insert(converter.converToLocal(delivery, new Delivery()));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public DeliveryDto getById(Long id) throws ServiceException {
        Delivery delivery;
        try {
            delivery = deliveryDAO.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.convertToFront(delivery);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            deliveryDAO.deleteById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DeliveryDto> getAll() throws ServiceException {
        List<Delivery> deliveries;
        try {
            deliveries = deliveryDAO.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<DeliveryDto> result = new ArrayList<>(deliveries.size());
        for (Delivery delivery: deliveries) {
            result.add(converter.convertToFront(delivery));
        }
        return result;
    }
}
