package om.dykyi.dao;

import om.dykyi.controller.ConnectionPool;
import om.dykyi.dao.guestbook.MysqlGuestbookDAO;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractModel {
    /**
     * Logging
     */
    public static final Logger LOGGER = Logger.getLogger(MysqlGuestbookDAO.class);

    /**
     * Connection to database
     */
    private static Connection connection;

    /**
     * Constructor
     */
    public AbstractModel() {

    }

    public Connection getConnection() {
        if(connection == null) {
            ConnectionPool conPool = ConnectionPool.getInstance();
            conPool.initDataSource();
            DataSource dataSource = conPool.getDataSource();
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return connection;
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
