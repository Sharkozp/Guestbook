package om.dykyi.controller;

import om.dykyi.system.PropertiesClass;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Класс пул соединений. Реализация паттерна Singleton.
 *
 * @author Дикий А.Н.
 * @version 2.0
 */
public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    /**
     * Logging
     */
    public static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static DataSource dataSource;


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
     * Init Data source from JNDI
     */
    public void initDataSource() {
        if (dataSource == null) {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/guestbook");
            } catch (NamingException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * Get Sata source
     *
     * @return dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}
