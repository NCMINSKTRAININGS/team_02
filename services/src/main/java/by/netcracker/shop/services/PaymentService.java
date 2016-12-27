package by.netcracker.shop.services;

import by.netcracker.shop.dto.PaymentDTO;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;


public interface PaymentService {

    void insert(PaymentDTO paymentDTO) throws ServiceException;

    PaymentDTO getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<PaymentDTO> getAll() throws ServiceException;
}
