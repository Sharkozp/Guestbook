package om.dykyi.models;

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
public class UserModel extends AbstractModel {
    private final static String admin = "select ADMIN from T_USER where USERNAME=?";
    private final static String getAllUsers = "select * from T_USER";
    private final static String getUser = "select * from T_USER where USERNAME=? and PWD_DIGEST=?";
    private final static String select = "select * from T_USER where USERNAME=?";
    private final static String insert = "INSERT INTO T_USER (USERNAME,PWD_DIGEST,LAST_NAME,FIRST_NAME) values(?,?,?,?)";
    private final static String delete = "delete from T_USER where USERNAME=?";
    private final static String update = "update T_USER set LAST_NAME=?,FIRST_NAME=? where USERNAME=?";

    /**
     * Constructor
     *
     * @param dataSource
     * @throws SQLException
     */
    public UserModel(DataSource dataSource) throws SQLException {
        super(dataSource);
    }

    /**
     * Метод добавляет в базу нового пользователя
     *
     * @param user     класс User
     * @param password дайджест пароля
     */
    public void addUser(User user, String password) {
        try {
            PreparedStatement pst = getPrepareStatement(insert);
            pst.setString(1, user.getUserName());
            pst.setString(2, password);
            pst.setString(3, user.getLastName());
            pst.setString(4, user.getFirstName());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
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
            PreparedStatement pst = getPrepareStatement(update);
            pst.setString(1, lastName);
            pst.setString(2, firstName);
            pst.setString(3, userName);
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод удаляет пользователя из базы
     *
     * @param userName учетное имя
     */
    public void deleteUser(String userName) {
        try {
            PreparedStatement pst = getPrepareStatement(delete);
            pst.setString(1, userName);
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
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
            PreparedStatement pst = getPrepareStatement(select);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(userName,
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
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
            PreparedStatement pst = getPrepareStatement(getUser);
            pst.setString(1, userName);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = rs.getString("USERNAME");
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return user;
    }

    /**
     * Метод возвращает из базы список пользователей
     *
     * @return список пользователей
     */
    public ArrayList<User> getListOfUsers() {
        ArrayList<User> list = new ArrayList<>();;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(getAllUsers);
            while (rs.next()) {
                list.add(new User(rs.getString("USERNAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME")));
            }
            rs.close();
            st.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
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
            PreparedStatement pst = getPrepareStatement(admin);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                isAdmin = rs.getBoolean("ADMIN");
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return isAdmin;
    }
}
