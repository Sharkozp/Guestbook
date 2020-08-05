package om.dykyi.dao;

import org.apache.log4j.Logger;
import om.dykyi.otherpack.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Паттерн DAO обслуживающий базу данных и пользователей
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
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
     * Логирование класса UserDAO.class
     */
    public static final Logger log = Logger.getLogger(UserDAO.class);

    /**
     * Экземпляр класса
     *
     * @param dataSource
     * @throws SQLException
     */
    public UserDAO(DataSource dataSource) throws SQLException {
        conn = dataSource.getConnection();
    }

    /**
     * Метод добавляет в базу нового пользователя
     *
     * @param user     класс User
     * @param password дайджест пароля
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
     * Метод обновляет пользователя в базе по заданным параметрам
     *
     * @param userName  учетное имя
     * @param lastName  фамилия пользователя
     * @param firstName имя пользователя
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
     * Метод удаляет пользователя из базы
     *
     * @param userName учетное имя
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
     * Метод возвращает пользователя по учетному имени
     *
     * @param userName учетное имя
     * @return класс User
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
     * Метод возвращает учетное имя пользователя с заданными параметрами из базы
     *
     * @param userName учетное имя
     * @param password дайджест пароля
     * @return учетное имя
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
     * Метод возвращает из базы список пользователей
     *
     * @return список пользователей
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
     * Метод проверяет является ли пользователь администратором
     *
     * @param userName учетное имя
     * @return признак администратора
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
