package om.dykyi.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс пул соединений. Реализация паттерна Singleton.
 *
 * @author Дикий А.Н.
 * @version 1.0
 */
public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    /**
     * Логирование класса ConnectionPool.class
     */
    public static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static BasicDataSource dataSource;
    PropertiesClass propClass;


    /**
     * Constructor
     */
    private ConnectionPool() {
    }

    /**
     * Singleton pattern
     *
     * @return instance
     */
    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Метод принимает все свойства из файла для создания пула соединений
     */
    public void setProperties() {
        propClass = PropertiesClass.getInstance();

        try {
            propClass.getDbConfig();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Метод закрывает все соединения с базой данных
     *
     * @throws SQLException
     */
    public void shutdownConnection() throws SQLException {
        dataSource.close();
    }

    /**
     * Метод возвращает источник данных
     *
     * @return connection
     */
    public void initDataSource() {
        setProperties();
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(propClass.getJdbcDriver());
        dataSource.setUrl(propClass.getURL());
        dataSource.setUsername(propClass.getUsername());
        dataSource.setPassword(propClass.getPassword());
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
