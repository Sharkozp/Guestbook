package om.dykyi.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

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

    private Connection connection;
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
     * @param connection
     * @throws SQLException
     */
    public static void shutdownConnection(Connection connection) throws SQLException {
        connection.close();
    }

    /**
     * Метод возвращает источник данных
     *
     * @return connection
     */
    public Connection getConnection() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(propClass.getJdbcDriver());
        bds.setUrl(propClass.getURL());
        bds.setUsername(propClass.getUsername());
        bds.setPassword(propClass.getPassword());
        connection = bds.getConnection();
        return connection;
    }
}
