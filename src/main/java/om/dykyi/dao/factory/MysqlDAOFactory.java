package om.dykyi.dao.factory;

import om.dykyi.controller.ConnectionPool;
import om.dykyi.dao.guestbook.GuestbookDAO;
import om.dykyi.dao.guestbook.MysqlGuestbookDAO;
import om.dykyi.dao.message.MessageDAO;
import om.dykyi.dao.message.MysqlMessageDAO;
import om.dykyi.dao.moderator.ModeratorDAO;
import om.dykyi.dao.moderator.MysqlModeratorDAO;
import om.dykyi.dao.user.MysqlUserDAO;
import om.dykyi.dao.user.UserDAO;

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

    public GuestbookDAO getGuestbookDao() {
        Connection connection = createConnection();
        return new MysqlGuestbookDAO(connection);
    }

    public MessageDAO getMessageDao() {
        Connection connection = createConnection();
        return new MysqlMessageDAO(connection);
    }

    public UserDAO getUserDao() {
        Connection connection = createConnection();
        return new MysqlUserDAO(connection);
    }

    public ModeratorDAO getModeratorDao() {
        Connection connection = createConnection();
        return new MysqlModeratorDAO(connection);
    }
}
