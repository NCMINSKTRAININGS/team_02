package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDao;
import by.netcracker.shop.dao.ProductImageDao;
import by.netcracker.shop.pojo.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productImageDao")
public class ProductImageDaoImpl extends AbstractDao<Integer, ProductImage> implements ProductImageDao {

    @Override
    public ProductImage finById(int id) {
        return getByKey(id);
    }

    @Override
    public List<ProductImage> findAll() {
        return (List<ProductImage>) createEntityCriteria().list();
    }

    @Override
    public void save(ProductImage image) {
        persist(image);
    }

    @Override
    public void deleteById(int id) {
        String hql = "delete from product_image where id=:id";
        getSession().createSQLQuery(hql).setParameter("id", id).executeUpdate();
    }
}
