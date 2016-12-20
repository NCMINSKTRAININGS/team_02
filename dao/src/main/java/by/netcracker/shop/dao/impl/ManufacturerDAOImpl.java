package by.netcracker.shop.dao.impl;

import by.netcracker.shop.dao.AbstractDAO;
import by.netcracker.shop.dao.ManufacturerDAO;
import by.netcracker.shop.pojo.Manufacturer;
import org.springframework.stereotype.Repository;

@Repository("manufacturerDAO")
public class ManufacturerDAOImpl extends AbstractDAO<Long, Manufacturer> implements ManufacturerDAO {

    public ManufacturerDAOImpl() {
        super();
    }
}
