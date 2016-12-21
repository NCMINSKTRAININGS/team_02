package by.netcracker.shop.services.impl;

import by.netcracker.shop.constants.ServiceConstants;
import by.netcracker.shop.dao.PaymentDAO;
import by.netcracker.shop.dto.PaymentDto;
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
    public void insert(PaymentDto payment) throws ServiceException {
        try {
            if (payment.getId() != null) {
                Payment entity = paymentDAO.getById(payment.getId());
                paymentDAO.insert(converter.convertToLocal(payment, entity));
            } else {
                paymentDAO.insert(converter.convertToLocal(payment, new Payment()));
            }
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    @Override
    public PaymentDto getById(Long id) throws ServiceException {
        Payment payment;
        try {
            payment = paymentDAO.getById(id);
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException();
        }
        return converter.convertToFront(payment);
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
    public List<PaymentDto> getAll() throws ServiceException {
        List<Payment> payments;
        try {
            payments = paymentDAO.getAll();
        } catch (DAOException e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        List<PaymentDto> result = new ArrayList<>(payments.size());
        for (Payment payment: payments) {
            result.add(converter.convertToFront(payment));
        }
        return result;
    }
}
