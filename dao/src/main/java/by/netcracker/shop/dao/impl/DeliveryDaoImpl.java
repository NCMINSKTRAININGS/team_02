package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.DeliveryDao;
import by.netcracker.shop.pojo.Delivery;
import org.springframework.stereotype.Repository;

@Repository("deliveryDao")
public class DeliveryDaoImpl extends AbstractDao<Long, Delivery> implements DeliveryDao{
    public DeliveryDaoImpl() {
        super("delivery");
    }
}
