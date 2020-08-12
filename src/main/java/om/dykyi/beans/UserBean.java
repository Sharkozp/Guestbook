package om.dykyi.beans;

import om.dykyi.dao.user.UserModel;
import om.dykyi.system.PasswordUtils;
import om.dykyi.system.PropertiesClass;
import org.apache.log4j.Logger;
import om.dykyi.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean - обьект модели данных для пользователя
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class UserBean {
    private UserModel userModel;
    private String userName;
    private String pwdDigest;
    private String lastName;
    private String firstName;
    private List<User> list;
    private boolean error;
    /**
     * Логирование класса UserBean.class
     */
    public static final Logger log = Logger.getLogger(UserBean.class);

    /**
     * Экземпляр класса
     */
    public UserBean() {
        userModel = new UserModel();
    }

    /**
     * Метод возвращает список пользователей
     *
     * @return список пользователей
     */
    public List<User> getList() {
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
     */
    public void setPwdDigest(String password) {
        PropertiesClass propertiesClass = PropertiesClass.getInstance();

        pwdDigest = PasswordUtils.generateSecurePassword(password, propertiesClass.getSystemKey());
    }



    /**
     * Метод проверяет является пользователь администратором
     *
     * @return признак
     */
    public boolean isAdmin(String userName) {
        return userModel.isAdmin(userName);
    }

    /**
     * Метод возвращает список всех пользователей
     */
    public void getListOfUsers() {
        list = userModel.getListOfUsers();
    }

    /**
     * Метод получает из базы данные о пользователе
     */
    public void getUser() {
        User user = userModel.getUser(userName);
        lastName = user.getLastName();
        firstName = user.getFirstName();
    }

    /**
     * Метод удаляет пользователя из базы
     */
    public void deleteUser() {
        userModel.deleteUser(userName);
    }

    /**
     * Метод создает пользователя в базе
     */
    public void addUser() {
        User user = new User(userName, lastName, firstName, true);
        userModel.addUser(user, pwdDigest);
    }

    /**
     * Метод обновляет пользователя в базе
     */
    public void updateUser() {
        userModel.updateUser(userName, lastName, firstName);
    }
}
