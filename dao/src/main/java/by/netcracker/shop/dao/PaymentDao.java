package by.netcracker.shop.dao;

import by.netcracker.shop.pojo.Payment;

import java.util.List;

/**
 * Created by j on 16.12.16.
 */
public interface PaymentDao {
    void save(Payment payment);
    Payment findById(Long id);
    void deleteById(Long id);
    List<Payment> findAll();
}
