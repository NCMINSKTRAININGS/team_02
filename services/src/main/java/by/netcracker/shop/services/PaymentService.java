package by.netcracker.shop.services;

import by.netcracker.shop.dto.PaymentDto;
import by.netcracker.shop.exceptions.ServiceException;

import java.util.List;


public interface PaymentService {

    void insert(PaymentDto payment) throws ServiceException;

    PaymentDto getById(Long id) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<PaymentDto> getAll() throws ServiceException;
}
