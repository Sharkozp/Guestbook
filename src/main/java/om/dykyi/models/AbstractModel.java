package om.dykyi.models;

import om.dykyi.controller.ConnectionPool;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractModel {
    /**
     * Logging
     */
    public static final Logger LOGGER = Logger.getLogger(GuestbookModel.class);

    /**
     * Connection to database
     */
    protected Connection connection;

    /**
     * Constructor
     *
     * @throws SQLException
     */
    public AbstractModel() throws SQLException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        conPool.setProperties();
        connection = conPool.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
