package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.AbstractEntity;

import java.io.Serializable;
import java.util.List;

public interface DAO <T extends AbstractEntity, K extends Serializable> {
    K insert(T entity) throws DAOException;

    T getById(K id) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean deleteById(K id) throws DAOException;

    List<T> getAll() throws DAOException;

    Long getCount() throws DAOException;

    List<T> getByGap(int offset, int quantity) throws DAOException;
}
