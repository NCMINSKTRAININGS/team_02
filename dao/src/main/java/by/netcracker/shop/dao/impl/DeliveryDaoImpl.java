package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.DeliveryDao;
import by.netcracker.shop.pojo.Delivery;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by j on 16.12.16.
 */
@Repository("deliveryDao")
public class DeliveryDaoImpl extends AbstractDao<Long, Delivery> implements DeliveryDao{
    @Override
    public void save(Delivery delivery) {
        persist(delivery);
    }

    @Override
    public Delivery findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void deleteById(Long id) {
        Query query =getSession().createSQLQuery("delete from delivery  where id=:id ");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public List<Delivery> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Delivery>) criteria.list();
    }
}
