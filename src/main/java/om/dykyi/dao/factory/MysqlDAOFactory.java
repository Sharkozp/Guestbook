package om.dykyi.dao.factory;

import om.dykyi.controller.ConnectionPool;
import om.dykyi.dao.guestbook.MysqlGuestbookDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDAOFactory extends DAOFactory {
    public static Connection createConnection() {
        ConnectionPool conPool = ConnectionPool.getInstance();
        conPool.initDataSource();
        DataSource dataSource = conPool.getDataSource();
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public MysqlGuestbookDAO getGuestbookDao() {
        Connection connection = createConnection();
        return new MysqlGuestbookDAO(connection);
    }
}
