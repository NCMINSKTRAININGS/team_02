package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.DeliveryDAO;
import by.netcracker.shop.pojo.Delivery;
import org.springframework.stereotype.Repository;

@Repository("deliveryDAO")
public class DeliveryDAOImpl extends AbstractDAO<Long, Delivery> implements DeliveryDAO {
    public DeliveryDAOImpl() {
        super("delivery");
    }
}
