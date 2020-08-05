package om.dykyi.models;

import om.dykyi.otherpack.Moderator;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн DAO обслуживающий базу данных и модераторов книг
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class ModeratorModel extends AbstractModel {
    private final static String SELECT_BY_USERNAME = "select * from T_MODERATOR where USERNAME=?";
    private final static String SELECT_BY_USERNAME_AND_GUESTBOOK = SELECT_BY_USERNAME + " and GUESTBOOK_NAME=?";
    private final static String INSERT_MODERATOR = "insert into T_MODERATOR (USERNAME,GUESTBOOK_NAME) values(?,?)";
    private final static String DELETE_MODERATOR = "delete from T_MODERATOR where ID=?";

    /**
     * Constructor
     *
     * @param dataSource
     * @throws SQLException
     */
    public ModeratorModel(DataSource dataSource) throws SQLException {
        super(dataSource);
    }

    /**
     * Метод добавляет в базу нового модератора по учетному имени к новой книге
     *
     * @param userName  учетное имя
     * @param guestbook имя гостевой книги
     */
    public void addModerator(String userName, String guestbook) {
        try {
            PreparedStatement pst = getPrepareStatement(INSERT_MODERATOR);
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
     * @param userName  учетное имя
     * @param guestbook имя гостевой книги
     * @return признак модератора
     */
    public boolean isModerator(String userName, String guestbook) {
        boolean isModerator = false;
        try {
            PreparedStatement pst = getPrepareStatement(SELECT_BY_USERNAME_AND_GUESTBOOK);
            pst.setString(1, userName);
            pst.setString(2, guestbook);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("USERNAME") != null
                        && rs.getString("GUESTBOOK_NAME") != null
                        && rs.getString("USERNAME").length() != 0
                        && rs.getString("GUESTBOOK_NAME").length() != 0) {
                    isModerator = true;
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
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
            PreparedStatement pst = getPrepareStatement(DELETE_MODERATOR);
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
            PreparedStatement pst = getPrepareStatement(SELECT_BY_USERNAME);
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
