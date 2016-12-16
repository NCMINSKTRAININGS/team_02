package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;

import java.io.Serializable;

public interface DAO <T>{
    Serializable insert(T entity) throws DAOException;

    T getById(Serializable id) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean deleteById(Serializable id) throws DAOException;
}
