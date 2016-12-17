package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.PaymentDao;
import by.netcracker.shop.pojo.Payment;
import org.springframework.stereotype.Repository;

@Repository("paymentDao")
public class PaymentDaoImpl extends AbstractDao<Long,Payment> implements PaymentDao {
    public PaymentDaoImpl() {
        super("payment");
    }
}
