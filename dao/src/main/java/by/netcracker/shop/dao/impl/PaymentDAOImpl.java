package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.PaymentDAO;
import by.netcracker.shop.pojo.Payment;
import org.springframework.stereotype.Repository;

@Repository("paymentDAO")
public class PaymentDAOImpl extends AbstractDAO<Long,Payment> implements PaymentDAO {
    public PaymentDAOImpl() {
        super();
    }
}
