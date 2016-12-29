package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.PaymentDAO;
import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.Payment;
import by.netcracker.shop.services.PaymentService;
import by.netcracker.shop.utils.PaymentConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private PaymentConverter converter;

    private static Logger logger = Logger.getLogger(PaymentServiceImpl.class);

    @Override
    public Long insert(PaymentDTO paymentDTO) throws ServiceException {
        Payment paymentPOJO = null;
        Long id;
        try {
            if (paymentDTO.getId() != null) {
                paymentPOJO = paymentDAO.getById(paymentDTO.getId());
            }
            if (paymentPOJO == null)
                paymentPOJO = new Payment();
            id = paymentDAO.insert(converter.toPaymentPOJO(paymentDTO, paymentPOJO));
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public PaymentDTO getById(Long id) throws ServiceException {
        Payment paymentPOJO;
        try {
            paymentPOJO = paymentDAO.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException();
        }
        return converter.toPaymentDTO(paymentPOJO);
    }

    @Override
    public void update(PaymentDTO paymentDTO) throws ServiceException {
        Payment paymentPOJO = null;
        try {
            if (paymentDTO.getId() != null)
                paymentPOJO = paymentDAO.getById(paymentDTO.getId());
            if (paymentPOJO == null) {
                throw new ServiceException();
            }
            paymentPOJO = converter.toPaymentPOJO(paymentDTO, paymentPOJO);
            paymentDAO.update(paymentPOJO);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            paymentDAO.deleteById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PaymentDTO> getAll() throws ServiceException {
        List<Payment> paymentPOJOs;
        List<PaymentDTO> paymentDTOs;
        try {
            paymentPOJOs = paymentDAO.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        paymentDTOs = new ArrayList<>(paymentPOJOs.size());
        for (Payment payment: paymentPOJOs) {
            paymentDTOs.add(converter.toPaymentDTO(payment));
        }
        return paymentDTOs;
    }

    @Override
    public Long getCount() throws ServiceException {
        Long count;
        try {
            count = paymentDAO.getCount();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public List<PaymentDTO> getByGap(int offset, int quantity) throws ServiceException {
        List<Payment> paymentPOJOs;
        List<PaymentDTO> paymentDTOs = new LinkedList<>();
        try {
            paymentPOJOs = paymentDAO.getByGap(offset, quantity);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (Payment paymentPOJO : paymentPOJOs) {
            paymentDTOs.add(converter.toPaymentDTO(paymentPOJO));
        }
        return paymentDTOs;
    }
}
