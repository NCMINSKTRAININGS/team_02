package by.netcracker.shop.dao.impl;

import by.netcracker.shop.constants.DAOConstants;
import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.ProductImageDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.Product;
import by.netcracker.shop.pojo.ProductImage;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productImageDAO")
public class ProductImageDAOImpl extends AbstractDAO<Long, ProductImage> implements ProductImageDAO {
    private static Logger logger = Logger.getLogger(ProductImageDAOImpl.class);

    public ProductImageDAOImpl() {
        super();
    }

    @Override
    public List<ProductImage> getImagesByProduct(Product product) throws DAOException {
        List<ProductImage> images;
        try {
            Criteria criteria = getSession().createCriteria(ProductImage.class);
            criteria.add(Restrictions.eq("product", product));
            images = criteria.list();
        } catch (HibernateException e) {
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException();
        }
        return images;
    }
}
