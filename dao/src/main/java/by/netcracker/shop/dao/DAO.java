package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.AbstractEntity;

import java.io.Serializable;

public interface DAO <T extends AbstractEntity>{
    Serializable insert(T entity) throws DAOException;

    T getById(int id) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean deleteById(int id) throws DAOException;
}
