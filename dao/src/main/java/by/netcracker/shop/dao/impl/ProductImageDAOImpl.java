package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productImageDAO")
public class ProductImageDAOImpl extends AbstractDAO<Long, ProductImage> implements ProductImageDAO {
    public ProductImageDAOImpl() {
        super();
    }

    @Override
    public List<ProductImage> getImagesByProduct(Product product) throws DAOException {
        List<ProductImage> images;
        Criteria criteria = getSession().createCriteria(ProductImage.class);
        criteria.add(Restrictions.eq("product", product));
        images = criteria.list();
        return images;
    }
}
