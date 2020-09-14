package om.dykyi.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;

public abstract class GenericDAO {
    /** Logging */
    public static final Logger LOGGER = Logger.getLogger(GenericDAO.class);
    protected Connection connection;

    protected GenericDAO(Connection connection) {
        this.connection = connection;
    }
}
