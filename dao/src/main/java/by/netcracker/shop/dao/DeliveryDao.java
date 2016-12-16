package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.Delivery;

import java.util.List;

/**
 * Created by j on 16.12.16.
 */
public interface DeliveryDao {

    void save(Delivery delivery);
    Delivery findById(Long id);
    void deleteById(Long id);
    List<Delivery> findAll();
}
