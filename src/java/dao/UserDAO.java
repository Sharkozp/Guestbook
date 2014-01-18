package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import otherpack.User;
import org.apache.log4j.Logger;

/**
 * ������� DAO ������������� ���� ������ � �������������
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class UserDAO {

    private Connection conn;
    private String admin = "select ADMIN from T_USER where USERNAME=?";
    private String getAllUsers = "select * from T_USER";
    private String getUser = "select * from T_USER where USERNAME=? and PWD_DIGEST=?";
    private String select = "select * from T_USER where USERNAME=?";
    private String insert = "INSERT INTO T_USER (USERNAME,PWD_DIGEST,"
            + "LAST_NAME,FIRST_NAME) values(?,?,?,?)";
    private String delete = "delete from T_USER where USERNAME=?";
    private String update = "update T_USER set LAST_NAME=?,"
            + "FIRST_NAME=? where USERNAME=?";
    /**
     * ����������� ������ UserDAO.class
     */
    public static final Logger log = Logger.getLogger(UserDAO.class);

    /**
     * ��������� ������
     *
     * @param dataSource
     * @throws SQLException
     */
    public UserDAO(DataSource dataSource) throws SQLException {
        conn = dataSource.getConnection();
    }

    /**
     * ����� ��������� � ���� ������ ������������
     *
     * @param user ����� User
     * @param password �������� ������
     */
    public void addUser(User user, String password) {
        try {
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, user.getUserName());
            pst.setString(2, password);
            pst.setString(3, user.getLastName());
            pst.setString(4, user.getFirstName());
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ��������� ������������ � ���� �� �������� ����������
     *
     * @param userName ������� ���
     * @param lastName ������� ������������
     * @param firstName ��� ������������
     */
    public void updateUser(String userName, String lastName, String firstName) {
        try {
            PreparedStatement pst = conn.prepareStatement(update);
            pst.setString(1, lastName);
            pst.setString(2, firstName);
            pst.setString(3, userName);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ������� ������������ �� ����
     *
     * @param userName ������� ���
     */
    public void deleteUser(String userName) {
        try {
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setString(1, userName);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ���������� ������������ �� �������� �����
     *
     * @param userName ������� ���
     * @return ����� User
     */
    public User getUser(String userName) {
        User user = null;
        try {
            PreparedStatement pst = conn.prepareStatement(select);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(userName,
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"));
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return user;
    }

    /**
     * ����� ���������� ������� ��� ������������ � ��������� ����������� �� ����
     *
     * @param userName ������� ���
     * @param password �������� ������
     * @return ������� ���
     */
    public String getUser(String userName, String password) {
        String user = null;
        try {
            PreparedStatement pst = conn.prepareStatement(getUser);
            pst.setString(1, userName);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = rs.getString("USERNAME");
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return user;
    }

    /**
     * ����� ���������� �� ���� ������ �������������
     *
     * @return ������ �������������
     */
    public ArrayList<User> getListOfUsers() {
        ArrayList<User> list = null;
        try {
            list = new ArrayList<User>();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(getAllUsers);
            while (rs.next()) {
                list.add(new User(rs.getString("USERNAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME")));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return list;
    }

    /**
     * ����� ��������� �������� �� ������������ ���������������
     *
     * @param userName ������� ���
     * @return ������� ��������������
     */
    public boolean isAdmin(String userName) {
        boolean isAdmin = false;
        try {
            PreparedStatement pst = conn.prepareStatement(admin);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                isAdmin = rs.getBoolean("ADMIN");
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return isAdmin;
    }
}
