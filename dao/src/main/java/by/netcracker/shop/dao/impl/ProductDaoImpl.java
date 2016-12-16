package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.ProductDao;
import by.netcracker.shop.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

    @Override
    public Product finById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) createEntityCriteria().list();
    }

    @Override
    public void save(Product product) {
        persist(product);
    }

    @Override
    public void deleteById(int id) {
        String hql = "delete from product where id=:id";
        getSession().createSQLQuery(hql).setParameter("id", id).executeUpdate();
    }
}
