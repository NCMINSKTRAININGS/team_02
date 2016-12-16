package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.PaymentDao;
import by.netcracker.shop.pojo.Payment;
import org.hibernate.Criteria;
import org.hibernate.Query;

import java.util.List;


public class PaymentDaoImpl extends AbstractDao<Long,Payment> implements PaymentDao {
    @Override
    public void save(Payment payment) {
        persist(payment);
    }

    @Override
    public Payment findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void deleteById(Long id) {
        Query query =getSession().createSQLQuery("delete from payment  where id=:id ");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public List<Payment> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Payment>) criteria.list();
    }
}
