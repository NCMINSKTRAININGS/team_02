package by.netcracker.shop.services;

import by.netcracker.shop.exceptions.ServiceException;
import by.netcracker.shop.pojo.AbstractEntity;

import java.io.Serializable;
import java.util.List;

public interface Service <T extends AbstractEntity, K extends Serializable> {
    K insert(T entity) throws ServiceException;

    T getById(K id) throws ServiceException;

    void update(T entity) throws ServiceException;

    void deleteById(K id) throws ServiceException;

    List<T> getAll() throws ServiceException;
}
