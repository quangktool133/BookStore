package com.bookstore.dao;

import com.bookstore.generator.IGenerator;
import com.bookstore.generator.SqlServerGenerator;
import com.bookstore.jdbc.JdbcConfig;
import com.bookstore.jdbc.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T, K> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    protected JdbcTemplate<T> jdbcTemplate;
    protected IGenerator<T> generator;

    public BaseDAO(Class<T> clazz) {
        this.generator = new SqlServerGenerator<>(clazz);
        this.jdbcTemplate = new JdbcTemplate<>(clazz);
    }

    public T findById(K id) {
        try {
            return this.jdbcTemplate
                    .select(this.generator.selectById(), id)
                    .executeQuery();
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            JdbcConfig.closeConnection();
            return null;
        }
    }

    public List<T> findAll() {
        try {
            return this.jdbcTemplate
                    .select(this.generator.selectAll())
                    .executeQueryList();
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            JdbcConfig.closeConnection();
            return new ArrayList<>();
        }
    }

    public void deleteById(K id) {
        try {
            this.jdbcTemplate
                    .delete(this.generator.deleteById(), id)
                    .executeSave();
            JdbcConfig.closeConnection();
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            JdbcConfig.closeConnection();
            throw new RuntimeException(ex.getMessage());
        }
    }
}