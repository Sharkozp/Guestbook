package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import otherpack.Guestbook;
import org.apache.log4j.Logger;

/**
 * ������� DAO ������������� ���� ������ � �������� �����
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class GuestbookDAO {

    private Connection conn;
    private String insert = "insert into T_GUESTBOOK(NAME,DESCRIPTION,DISPLAY_ORDER) values(?,?,?)";
    private String select = "select * from T_GUESTBOOK";
    private String get = "select * from T_GUESTBOOK where NAME=?";
    private String delete = "delete from T_GUESTBOOK where NAME=?";
    private String update = "update T_GUESTBOOK set DESCRIPTION=?,"
            + "DISPLAY_ORDER=? where NAME=?";
    /**
     * ����������� ������ GuestbookDAO.class
     */
    public static final Logger log = Logger.getLogger(GuestbookDAO.class);

    /**
     * ��������� ������
     * @param dataSource 
     * @throws SQLException
     */
    public GuestbookDAO(DataSource dataSource) throws SQLException {
        conn = dataSource.getConnection();
    }

    /**
     * ����� ��������� ����� ����� � ����
	 *
     * @param guestbook ����� �����
     */
    public void addGuestbook(Guestbook guestbook) {
        try {
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, guestbook.getName());
            pst.setString(2, guestbook.getDescription());
            pst.setInt(3, guestbook.getDisplayOrder());
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ������� ����� �� ���� ������ �� �����
	 *
     * @param guestbookName ��� ���� ������
     */
    public void deleteGuestbook(String guestbookName) {
        try {
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setString(1, guestbookName);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ��������� ������ �������� �����
	 *
     * @param guestbook ����� �����
     */
    public void updateGuestbook(Guestbook guestbook) {
        try {
            PreparedStatement pst = conn.prepareStatement(update);
            pst.setString(1, guestbook.getDescription());
            pst.setInt(2, guestbook.getDisplayOrder());
            pst.setString(3, guestbook.getName());
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ���������� ����� �� ���� ����� �� �����
	 *
     * @param guestbookName ��� �����
     * @return ����� �����
     */
    public Guestbook getGuestbook(String guestbookName) {
        Guestbook g = null;
        try {
            PreparedStatement pst = conn.prepareStatement(get);
            pst.setString(1, guestbookName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                g = new Guestbook(guestbookName,
                        rs.getString("description"),
                        rs.getInt("display_order"));
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return g;
    }

    /**
     * ����� ���������� ������ ���� ���� �� ����
	 *
     * @return ������ ���� ����
     */
    public ArrayList<Guestbook> getGuestbookList() {
        ArrayList<Guestbook> list = null;
        try {
            list = new ArrayList<Guestbook>();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                list.add(new Guestbook(rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("display_order")));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return list;
    }
}
