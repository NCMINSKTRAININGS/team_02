package by.netcracker.shop.dao;

import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * This interface <code>DAO</code> is describes the behavior methods of an abstract entity.
 * @param <T> the type of elements in this DAO
 * @param <K> the type of entity identifier in this DAO
 */
public interface DAO <T extends AbstractEntity<K>, K extends Serializable> {
    /**
     * Insert entity in the storage
     * @param entity inherited from AbstractEntity
     * @return a primary key of persisted entity
     * @throws DAOException if there was an error in the request
     */
    K insert(T entity) throws DAOException;

    /**
     * Finds entity by its id in the storage
     * @param id is the entity identifier
     * @return a entity inherited from AbstractEntity
     * @throws DAOException if there was an error in the request
     */
    T getById(K id) throws DAOException;

    /**
     * Updates entity in the storage
     * @param entity inherited from AbstractEntity
     * @throws DAOException if there was an error in the request
     */
    void update(T entity) throws DAOException;

    /**
     * Deletes entity by its id in the storage
     * @param id is the entity identifier
     * @throws DAOException if there was an error in the request
     */
    void deleteById(K id) throws DAOException;

    /**
     * Finds all entities from the storage
     * @return a list of entity inherited from AbstractEntity
     * @throws DAOException if there was an error in the request
     */
    List<T> getAll() throws DAOException;

    /**
     * Gets the count of stored entities
     * @return the count of stored entities
     * @throws DAOException if there was an error in the request
     */
    Long getCount() throws DAOException;

    /**
     * Returns a list of the entities starting from <tt>offset</tt> to <tt>quantity</tt> number of entities.
     * @param offset is the offset from which you need to start a sampling
     * @param quantity is the number of entities
     * @return a list of entity inherited from AbstractEntity
     * @throws DAOException if there was an error in the request
     */
    List<T> getByGap(int offset, int quantity) throws DAOException;
}
