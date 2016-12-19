package by.netcracker.shop.dao.impl;

import by.netcracker.shop.constants.DAOConstants;
import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.UserDAO;
import by.netcracker.shop.exceptions.DAOException;
import by.netcracker.shop.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO<Long, User> implements UserDAO {
    private static Logger logger = Logger.getLogger(AbstractDAO.class);
    private static final String GET_BY_USERNAME_WITH_PASSWORD_WITH_SALT = "SELECT * FROM user WHERE username=:username " +
            "AND password=:password AND salt=:salt";
    private static final String GET_BY_USERNAME = "SELECT * FROM user WHERE username=:username";

    public UserDAOImpl() {
        super();
    }

    @Override
    public User getByUsername(String username) throws DAOException {
        User user;
        try {
            Session session = getSession();
            Query query = session.createSQLQuery(GET_BY_USERNAME)
                    .addEntity(User.class)
                    .setParameter("username", username);
            user = (User) query.uniqueResult();
        }
        catch(HibernateException e){
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public User getByUsernamePasswordSalt(String username, String password, String salt) throws DAOException {
        User user;
        try {
            Session session = getSession();
            Query query = session.createSQLQuery(GET_BY_USERNAME_WITH_PASSWORD_WITH_SALT)
                    .addEntity(User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .setParameter("salt", salt);
            user = (User) query.uniqueResult();
        }
        catch(HibernateException e){
            logger.error(DAOConstants.ERROR_DAO, e);
            throw new DAOException(e);
        }
        return user;
    }
}
