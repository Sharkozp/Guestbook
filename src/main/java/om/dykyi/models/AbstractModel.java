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
    private final ConnectionPool conPool;

    /**
     * Connection to database
     */
    private Connection connection;

    /**
     * Constructor
     */
    public AbstractModel() {
        conPool = ConnectionPool.getInstance();
    }

    public Connection getConnection() throws SQLException{
        connection = conPool.getDataSource().getConnection();
        return connection;
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
