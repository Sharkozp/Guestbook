package om.dykyi.beans;

import om.dykyi.models.UserModel;
import org.apache.log4j.Logger;
import om.dykyi.otherpack.User;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JavaBean - обьект модели данных для пользователя
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class UserBean {

    private String userName;
    private String pwdDigest;
    private String lastName;
    private String firstName;
    private ArrayList<User> list;
    private boolean error;
    private DataSource dataSource;
    /**
     * Логирование класса UserBean.class
     */
    public static final Logger log = Logger.getLogger(UserBean.class);

    /**
     * Экземпляр класса
     */
    public UserBean() {
    }

    /**
     * Метод возвращает список пользователей
     *
     * @return список пользователей
     */
    public ArrayList<User> getList() {
        return list;
    }

    /**
     * Метод принимает список пользователей
     *
     * @param list список пользователей
     */
    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    /**
     * Метод возвращает учетное имя
     *
     * @return учетное имя
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Метод принимает учетное имя
     *
     * @param userName учетное имя
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Метод возвращает дайджест пароля
     *
     * @return дайджест пароля
     */
    public String getPwdDigest() {
        return pwdDigest;
    }

    /**
     * Метод возвращает фамилию пользователя
     *
     * @return фамилию пользователя
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Метод принимает фамилию пользователя
     *
     * @param lastName фамилию пользователя
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Метод возвращает имя пользователя
     *
     * @return имя пользователя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Метод принимает имя пользователя
     *
     * @param firstName имя пользователя
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Метод возвращает ошибку
     *
     * @return ошибка
     */
    public boolean isError() {
        return error;
    }

    /**
     * Метод принимает ошибку
     *
     * @param error ошибка
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * Метод принимает строку для создания дайджеста пароля
     *
     * @param password пароль
     * @throws NoSuchAlgorithmException
     */
    public void setPwdDigest(String password) throws NoSuchAlgorithmException {
        pwdDigest = new String();
        byte[] pwdByteArray;
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        pwdByteArray = md.digest();
        for (byte b : pwdByteArray) {
            pwdDigest += (new Byte(b)).intValue();
        }
    }

    /**
     * Метод проверяет существует ли пользователь в базе данных
     *
     * @return признак
     */
    public boolean isUserExist() {
        String user = new String();
        try {
            UserModel uDAO = new UserModel();
            user = uDAO.getUser(userName, pwdDigest);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        if (user == (null) || user.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Метод проверяет является пользователь администратором
     *
     * @return признак
     */
    public boolean isAdmin() {
        boolean isAdmin = false;
        try {
            UserModel uDAO = new UserModel();
            isAdmin = uDAO.isAdmin(userName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return isAdmin;
    }

    /**
     * Метод возвращает список всех пользователей
     */
    public void getListOfUsers() {
        try {
            UserModel uDAO = new UserModel();
            list = uDAO.getListOfUsers();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод получает из базы данные о пользователе
     */
    public void getUser() {
        try {
            UserModel uDAO = new UserModel();
            User u = uDAO.getUser(userName);
            lastName = u.getLastName();
            firstName = u.getFirstName();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод удаляет пользователя из базы
     */
    public void deleteUser() {
        try {
            UserModel uDAO = new UserModel();
            uDAO.deleteUser(userName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод создает пользователя в базе
     */
    public void addUser() {
        try {
            UserModel uDAO = new UserModel();
            uDAO.addUser(new User(
                    userName,
                    lastName,
                    firstName), pwdDigest);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод обновляет пользователя в базе
     */
    public void updateUser() {
        try {
            UserModel uDAO = new UserModel();
            uDAO.updateUser(userName, lastName, firstName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод принимает источник данных DataSource
     *
     * @param dataSource источник данных
     */
    public void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            log.error("DataSource is not set");
        } else {
            this.dataSource = dataSource;
        }
    }
}
