package om.dykyi.models;

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
     * @param dataSource
     * @throws SQLException
     */
    public AbstractModel(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
