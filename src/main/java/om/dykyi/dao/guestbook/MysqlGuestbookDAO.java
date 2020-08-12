package om.dykyi.dao.guestbook;

import om.dykyi.dao.GenericDAO;
import om.dykyi.models.Guestbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн DAO обслуживающий базу данных и гостевые книги
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class MysqlGuestbookDAO extends GenericDAO implements GuestbookDAO {
    private final static String INSERT_GUESTBOOK = "insert into guestbooks(NAME, DESCRIPTION, DISPLAY_ORDER) values(?,?,?)";
    private final static String SELECT_GUESTBOOK = "select * from guestbooks";
    private final static String UPDATE_GUESTBOOK = "update guestbooks set NAME = ?, DESCRIPTION = ?, DISPLAY_ORDER = ? where guestbook_id = ?";
    private final static String GET_BY_ID = "select * from guestbooks where guestbook_id = ?";
    private final static String DELETE_BY_NAME = "delete from guestbooks where guestbook_id = ?";

    public MysqlGuestbookDAO(Connection connection) {
        super(connection);
    }

    /**
     * Add guestbook
     *
     * @param guestbook guestbook class
     */
    public int addGuestbook(Guestbook guestbook) {
        int result = 0;
        try {
            PreparedStatement pst = connection.prepareStatement(INSERT_GUESTBOOK);
            pst.setString(1, guestbook.getName());
            pst.setString(2, guestbook.getDescription());
            pst.setInt(3, guestbook.getDisplayOrder());
            result = pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return result;
    }

    /**
     * Delete guestbook
     *
     * @param guestbookId guestbook ID
     */
    public boolean deleteGuestbook(int guestbookId) {
        boolean result = false;
        try {
            PreparedStatement pst = connection.prepareStatement(DELETE_BY_NAME);
            pst.setInt(1, guestbookId);
            int lines = pst.executeUpdate();
            result = lines > 0;
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return result;
    }

    @Override
    public Guestbook findGuestbook(Guestbook guestbook) {
        return null;
    }

    /**
     * Update guestbook
     *
     * @param guestbook Guestbook class
     */
    public boolean updateGuestbook(Guestbook guestbook) {
        boolean result = false;
        try {
            PreparedStatement pst = connection.prepareStatement(UPDATE_GUESTBOOK);
            pst.setString(1, guestbook.getName());
            pst.setString(2, guestbook.getDescription());
            pst.setInt(3, guestbook.getDisplayOrder());
            pst.setInt(4, guestbook.getId());
            int lines = pst.executeUpdate();
            result = lines > 0;
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return result;
    }

    /**
     * Get guestbook by ID
     *
     * @param guestbookId guestbook ID
     * @return guestbook class
     */
    public Guestbook getGuestbook(int guestbookId) {
        Guestbook guestbook = null;
        try {
            PreparedStatement pst = connection.prepareStatement(GET_BY_ID);
            pst.setInt(1, guestbookId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                guestbook = new Guestbook(
                        guestbookId,
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("display_order")
                );
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return guestbook;
    }

    /**
     * Метод возвращает список всех книг из базы
     *
     * @return список всех книг
     */
    public List<Guestbook> getGuestbookList() {
        List<Guestbook> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SELECT_GUESTBOOK);
            while (rs.next()) {
                list.add(new Guestbook(
                        rs.getInt("guestbook_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("display_order")
                ));
            }
            rs.close();
            st.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return list;
    }
}
