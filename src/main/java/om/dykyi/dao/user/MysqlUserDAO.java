package om.dykyi.dao.user;

import om.dykyi.dao.AbstractModel;
import om.dykyi.dao.GenericDAO;
import om.dykyi.beans.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Паттерн DAO обслуживающий базу данных и пользователей
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class MysqlUserDAO extends GenericDAO implements UserDAO {
    private final static String GET_ADMIN = "select IS_ADMIN from users where USERNAME = ?";
    private final static String getAllUsers = "select * from users";
    private final static String GET_USER_BY_USERNAME = "select * from users where USERNAME = ?";
    private final static String GET_USER_BY_USER_ID = "select * from users where USER_ID = ?";
    private final static String insert = "INSERT INTO users (USERNAME,PWD_DIGEST,LAST_NAME,FIRST_NAME) values(?,?,?,?)";
    private final static String delete = "delete from users where USER_ID=?";
    private final static String update = "update users set LAST_NAME=?,FIRST_NAME=? where USERNAME=?";

    /**
     * Constructor
     */
    public MysqlUserDAO(Connection connection) {
        super(connection);
    }

    /**
     * Метод добавляет в базу нового пользователя
     *
     * @param user     класс User
     * @param password дайджест пароля
     */
    public void addUser(User user, String password) {
        try {
            PreparedStatement pst = connection.prepareStatement(insert);
            pst.setString(1, user.getUsername());
            pst.setString(2, password);
            pst.setString(3, user.getLastName());
            pst.setString(4, user.getFirstName());
            pst.executeUpdate();
            pst.close();
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
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setString(1, lastName);
            pst.setString(2, firstName);
            pst.setString(3, userName);
            pst.executeUpdate();
            pst.close();
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
            PreparedStatement pst = connection.prepareStatement(delete);
            //TODO change to user_id
            pst.setString(1, userName);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод возвращает пользователя по учетному имени
     *
     * @param username username
     * @return класс User
     */
    public User getUser(String username) {
        User user = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(GET_USER_BY_USERNAME);
            pst.setString(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt("USER_ID"),
                        username,
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getBoolean("IS_ADMIN")
                );
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
        }
        return user;
    }

    /**
     * Метод возвращает пользователя по учетному имени
     *
     * @param userId user ID
     * @return class User
     */
    public User getUserById(int userId) {
        User user = null;
        try {
            PreparedStatement pst = connection.prepareStatement(GET_USER_BY_USER_ID);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getString("USERNAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getBoolean("IS_ADMIN")
                );
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return user;
    }

    /**
     * Get password digest from database
     *
     * @param username username
     * @return password digest
     */
    public String getUserDigest(String username) {
        String pwdDigest = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(GET_USER_BY_USERNAME);
            pst.setString(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                pwdDigest = rs.getString("pwd_digest");
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
        return pwdDigest;
    }

    /**
     * Метод возвращает из базы список пользователей
     *
     * @return список пользователей
     */
    public ArrayList<User> getListOfUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(getAllUsers);
            while (rs.next()) {
                list.add(new User(rs.getString("USERNAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getBoolean("IS_ADMIN")));
            }
            rs.close();
            st.close();
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
            PreparedStatement pst = connection.prepareStatement(GET_ADMIN);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                isAdmin = rs.getBoolean("ADMIN");
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return isAdmin;
    }
}
