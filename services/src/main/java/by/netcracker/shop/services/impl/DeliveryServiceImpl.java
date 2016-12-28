package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.DeliveryDAO;
import by.netcracker.shop.dto.DeliveryDTO;
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
import java.util.LinkedList;
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
    public Long insert(DeliveryDTO deliveryDTO) throws ServiceException {
        Delivery deliveryPOJO = null;
        Long id;
        try {
            if (deliveryDTO.getId() != null) {
                deliveryPOJO = deliveryDAO.getById(deliveryDTO.getId());
            }
            if (deliveryPOJO == null)
                deliveryPOJO = new Delivery();
            id = deliveryDAO.insert(converter.toDeliveryPOJO(deliveryDTO, deliveryPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public DeliveryDTO getById(Long id) throws ServiceException {
        Delivery deliveryPOJO;
        try {
            deliveryPOJO = deliveryDAO.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return converter.toDeliveryDTO(deliveryPOJO);
    }

    @Override
    public void update(DeliveryDTO deliveryDTO) throws ServiceException {
        Delivery deliveryPOJO = null;
        try {
            if (deliveryDTO.getId() != null)
                deliveryPOJO = deliveryDAO.getById(deliveryDTO.getId());
            if (deliveryPOJO == null) {
                throw new ServiceException();
            }
            deliveryPOJO = converter.toDeliveryPOJO(deliveryDTO, deliveryPOJO);
            deliveryDAO.update(deliveryPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
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
    public List<DeliveryDTO> getAll() throws ServiceException {
        List<Delivery> deliveryPOJOs;
        List<DeliveryDTO> deliveryDTOs;
        try {
            deliveryPOJOs = deliveryDAO.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        deliveryDTOs = new ArrayList<>(deliveryPOJOs.size());
        for (Delivery delivery : deliveryPOJOs) {
            deliveryDTOs.add(converter.toDeliveryDTO(delivery));
        }
        return deliveryDTOs;
    }

    @Override
    public Long getCount() throws ServiceException {
        Long count;
        try {
            count = deliveryDAO.getCount();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public List<DeliveryDTO> getByGap(int offset, int quantity) throws ServiceException {
        List<Delivery> deliveryPOJOs;
        List<DeliveryDTO> deliveryDTOs = new LinkedList<>();
        try {
            deliveryPOJOs = deliveryDAO.getByGap(offset, quantity);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (Delivery deliveryPOJO : deliveryPOJOs) {
            deliveryDTOs.add(converter.toDeliveryDTO(deliveryPOJO));
        }
        return deliveryDTOs;
    }
}
