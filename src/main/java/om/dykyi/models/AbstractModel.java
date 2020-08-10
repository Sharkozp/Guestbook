package om.dykyi.models;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class AbstractModel {
    /**
     * Logging
     */
    public static final Logger LOGGER = Logger.getLogger(GuestbookModel.class);

    /**
     * Connection to database
     */
    private static Connection connection;

    /**
     * Constructor
     */
    public AbstractModel() {
        // TODO Move to singleton/Separate class
        Context ctx = null;
        try {
            ctx = new InitialContext();

            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/guestbook");

            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
