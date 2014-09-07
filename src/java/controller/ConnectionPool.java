package controller;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * Класс пул соединений. Реализация паттерна Singleton.
 *
 * @version 1.0
 * @author Дикий А.Н.
 */
public class ConnectionPool {

    private DataSource dataSource;
    PropertiesClass propClass;
    private static final ConnectionPool instance = new ConnectionPool();
    /**
     * Логирование класса ConnectionPool.class
     */
    public static final Logger log = Logger.getLogger(ConnectionPool.class);

    /**
     * Экземпляр класса
     */
    private ConnectionPool() {
    }

    /**
     * Статический метод возвращает единственный экземпляр данного класса
     *
     * @return единственный экземпляр данного класса
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
            log.error(e.getMessage());
        }

        dataSource = setupDataSource(propClass.getJDBC_driver(), propClass.getURL());
    }

    /**
     * Метод создания пула соединений
     *
     * @param dbDriver
     * @param dbURL
     * @return
     */
    public static DataSource setupDataSource(String dbDriver, String dbURL) {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(dbDriver);
        bds.setUrl(dbURL);
        return bds;
    }

    /**
     * Метод закрывает все соединения с базой данных
     *
     * @param dataSource
     * @throws SQLException
     */
    public static void shutdownDataSource(DataSource dataSource) throws SQLException {
        BasicDataSource bds = (BasicDataSource) dataSource;
        bds.close();
    }

    /**
     * Метод возвращает источник данных
     *
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}
