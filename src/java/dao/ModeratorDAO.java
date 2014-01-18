package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import otherpack.Moderator;
import org.apache.log4j.Logger;

/**
 * ������� DAO ������������� ���� ������ � ����������� ����
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class ModeratorDAO {

    private Connection conn;
    private String moder = "select * from T_MODERATOR where USERNAME=? and GUESTBOOK_NAME=?";
    private String select = "select * from T_MODERATOR where USERNAME=?";
    private String insert = "insert into T_MODERATOR (USERNAME,GUESTBOOK_NAME) values(?,?)";
    private String delete = "delete from T_MODERATOR where ID=?";
    /**
     * ����������� ������ ModeratorDAO.class
     */
    public static final Logger log = Logger.getLogger(ModeratorDAO.class);

    /**
     * ��������� ������
     *
     * @param dataSource
     * @throws SQLException
     */
    public ModeratorDAO(DataSource dataSource) throws SQLException {
        conn = dataSource.getConnection();
    }

    /**
     * ����� ��������� � ���� ������ ���������� �� �������� ����� � ����� �����
     *
     * @param userName ������� ���
     * @param guestbook ��� �������� �����
     */
    public void addModerator(String userName, String guestbook) {
        try {
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, userName);
            pst.setString(2, guestbook);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ��������� � ���� �������� ������������ ����������� ������ �����
     *
     * @param userName ������� ���
     * @param guestbook ��� �������� �����
     * @return ������� ����������
     */
    public boolean isModerator(String userName, String guestbook) {
        boolean isModerator = false;
        try {
            PreparedStatement pst = conn.prepareStatement(moder);
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
            log.error(se.getMessage());
        }
        return isModerator;
    }

    /**
     * ����� ������� �� ���� ���������� ����� �� ID
     *
     * @param id
     */
    public void deleteModeratorBook(int id) {
        try {
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ���������� ���� ������ ����������� ���� �� ����
     *
     * @param userName ������� ��� ������������ 
     * @return ������ ����������� ����
     */
    public ArrayList<Moderator> getListOfModeratorsBook(String userName) {
        ArrayList<Moderator> list = new ArrayList<Moderator>();
        try {
            PreparedStatement pst = conn.prepareStatement(select);
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
            log.error(se.getMessage());
        }
        return list;
    }
}
