package om.dykyi.dao.moderator;

import om.dykyi.dao.GenericDAO;
import om.dykyi.models.Moderator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Паттерн DAO обслуживающий базу данных и модераторов книг
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class MysqlModeratorDAO extends GenericDAO implements ModeratorDAO {
    private final static String SELECT_BY_USER_ID = "select * from moderators where USER_ID=?";
    private final static String SELECT_BY_USER_ID_AND_GUESTBOOK_ID = SELECT_BY_USER_ID + " and GUESTBOOK_ID=?";
    private final static String INSERT_MODERATOR = "insert into t_moderator (USERNAME,GUESTBOOK_NAME) values(?,?)";
    private final static String DELETE_MODERATOR = "delete from t_moderator where ID=?";

    /**
     * Constructor
     */
    public MysqlModeratorDAO(Connection connection) {
        super(connection);
    }

    /**
     * Метод добавляет в базу нового модератора по учетному имени к новой книге
     *
     * @param userName  учетное имя
     * @param guestbook имя гостевой книги
     */
    public void addModerator(String userName, String guestbook) {
        try {
            PreparedStatement pst = connection.prepareStatement(INSERT_MODERATOR);
            pst.setString(1, userName);
            pst.setString(2, guestbook);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод проверяет в базе является пользователь модератором данной книги
     *
     * @param userId      учетное имя
     * @param guestbookId имя гостевой книги
     * @return признак модератора
     */
    public boolean isModerator(int userId, int guestbookId) {
        boolean isModerator = false;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(SELECT_BY_USER_ID_AND_GUESTBOOK_ID);
            pst.setInt(1, userId);
            pst.setInt(2, guestbookId);
            rs = pst.executeQuery();
            if (rs.next()) {
                isModerator = true;
            }
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.error(throwables.getMessage());
            }
        }

        return isModerator;
    }

    /**
     * Метод удаляет из базы модератора книги по ID
     *
     * @param id
     */
    public void deleteModeratorBook(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement(DELETE_MODERATOR);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод возвращает весь список модераторов книг из базы
     *
     * @param userName учетное имя пользователя
     * @return список модераторов книг
     */
    public ArrayList<Moderator> getListOfModeratorsBook(String userName) {
        ArrayList<Moderator> list = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement(SELECT_BY_USER_ID);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Moderator(
                        rs.getInt("ID"),
                        rs.getString("GUESTBOOK_NAME"),
                        userName));
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return list;
    }
}
