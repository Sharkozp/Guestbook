package om.dykyi.dao;

import java.sql.Connection;

public abstract class GenericDAO {
    protected Connection connection;

    protected GenericDAO(Connection connection) {
        this.connection = connection;
    }
}
