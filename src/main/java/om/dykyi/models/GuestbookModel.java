package om.dykyi.models;

import om.dykyi.otherpack.Guestbook;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Паттерн DAO обслуживающий базу данных и гостевые книги
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class GuestbookModel extends AbstractModel {
    private final static String INSERT_GUESTBOOK = "insert into t_guestbook(NAME,DESCRIPTION,DISPLAY_ORDER) values(?,?,?)";
    private final static String SELECT_GUESTBOOK = "select * from t_guestbook";
    private final static String UPDATE_GUESTBOOK = "update t_guestbook set DESCRIPTION=?, DISPLAY_ORDER=? where NAME=?";
    private final static String GET_BY_NAME = "select * from t_guestbook where NAME=?";
    private final static String DELETE_BY_NAME = "delete from t_guestbook where NAME=?";

    /**
     * Constructor
     */
    public GuestbookModel() {
    }

    /**
     * Метод добавляет новую книгу в базу
     *
     * @param guestbook класс книги
     */
    public void addGuestbook(Guestbook guestbook) {
        try {
            PreparedStatement pst = getPrepareStatement(INSERT_GUESTBOOK);
            pst.setString(1, guestbook.getName());
            pst.setString(2, guestbook.getDescription());
            pst.setInt(3, guestbook.getDisplayOrder());
            pst.executeUpdate();
            pst.close();
            getConnection().close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод удаляет книгу из базы данных по имени
     *
     * @param guestbookName имя базы данных
     */
    public void deleteGuestbook(String guestbookName) {
        try {
            PreparedStatement pst = getPrepareStatement(DELETE_BY_NAME);
            pst.setString(1, guestbookName);
            pst.executeUpdate();
            pst.close();
            getConnection().close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод обновляет записи гостевой книги
     *
     * @param guestbook класс книги
     */
    public void updateGuestbook(Guestbook guestbook) {
        try {
            PreparedStatement pst = getPrepareStatement(UPDATE_GUESTBOOK);
            pst.setString(1, guestbook.getDescription());
            pst.setInt(2, guestbook.getDisplayOrder());
            pst.setString(3, guestbook.getName());
            pst.executeUpdate();
            pst.close();
            getConnection().close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод позвращает книгу из базы даных по имени
     *
     * @param guestbookName имя книги
     * @return класс книги
     */
    public Guestbook getGuestbook(String guestbookName) {
        Guestbook g = null;
        try {
            PreparedStatement pst = getPrepareStatement(GET_BY_NAME);
            pst.setString(1, guestbookName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                g = new Guestbook(guestbookName, rs.getString("description"), rs.getInt("display_order"));
            }
            rs.close();
            pst.close();
            getConnection().close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return g;
    }

    /**
     * Метод возвращает список всех книг из базы
     *
     * @return список всех книг
     */
    public ArrayList<Guestbook> getGuestbookList() {
        ArrayList<Guestbook> list = new ArrayList<>();
        try {

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(SELECT_GUESTBOOK);
            while (rs.next()) {
                list.add(new Guestbook(rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("display_order")));
            }
            rs.close();
            st.close();
            getConnection().close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return list;
    }
}
