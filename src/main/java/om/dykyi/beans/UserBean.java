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
    private UserModel uDAO;
    private String userName;
    private String pwdDigest;
    private String lastName;
    private String firstName;
    private ArrayList<User> list;
    private boolean error;
    /**
     * Логирование класса UserBean.class
     */
    public static final Logger log = Logger.getLogger(UserBean.class);

    /**
     * Экземпляр класса
     */
    public UserBean() {
        uDAO = new UserModel();
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
        String user = uDAO.getUser(userName, pwdDigest);

        return user != (null) && user.length() != 0;
    }

    /**
     * Метод проверяет является пользователь администратором
     *
     * @return признак
     */
    public boolean isAdmin() {
        return uDAO.isAdmin(userName);
    }

    /**
     * Метод возвращает список всех пользователей
     */
    public void getListOfUsers() {
        list = uDAO.getListOfUsers();
    }

    /**
     * Метод получает из базы данные о пользователе
     */
    public void getUser() {
        User u = uDAO.getUser(userName);
        lastName = u.getLastName();
        firstName = u.getFirstName();
    }

    /**
     * Метод удаляет пользователя из базы
     */
    public void deleteUser() {
        uDAO.deleteUser(userName);
    }

    /**
     * Метод создает пользователя в базе
     */
    public void addUser() {
        uDAO.addUser(new User(userName, lastName, firstName), pwdDigest);
    }

    /**
     * Метод обновляет пользователя в базе
     */
    public void updateUser() {
        uDAO.updateUser(userName, lastName, firstName);
    }
}
