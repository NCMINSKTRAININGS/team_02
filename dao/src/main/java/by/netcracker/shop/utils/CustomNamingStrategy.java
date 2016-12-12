package by.netcracker.shop.utils;

import org.hibernate.cfg.DefaultNamingStrategy;

public class CustomNamingStrategy extends DefaultNamingStrategy {
    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className).toLowerCase();
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return super.propertyToColumnName(propertyName).toLowerCase();
    }
}