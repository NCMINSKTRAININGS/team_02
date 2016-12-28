package by.netcracker.shop.services;

import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;


public interface PaymentService {
    Long insert(PaymentDTO paymentDTO) throws ServiceException;

    PaymentDTO getById(Long id) throws ServiceException;

    void update(PaymentDTO paymentDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<PaymentDTO> getAll() throws ServiceException;

    Long getCount() throws ServiceException;

    List<PaymentDTO> getByGap(int offset, int quantity) throws ServiceException;
}
