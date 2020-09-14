package om.dykyi.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean - обьект модели данных для авторизации
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class Login implements Serializable {
    private String userName;
    private boolean isModerator;
    private boolean isAdmin;

    /**
     * Экземпляр класса
     */
    public Login() {
    }

    public Login(String userName, boolean isModerator, boolean isAdmin) {
        this.userName = userName;
        this.isModerator = isModerator;
        this.isAdmin = isAdmin;
    }

    /**
     * Метод возвращает учетное имя пользователя
     *
     * @return учетное имя
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Метод принимает учетное имя пользователя
     *
     * @param userName учетное имя
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Метод возвращает признак, является ли пользователь модератором для
     * гостевой книги
     *
     * @return признак
     */
    public boolean isModerator() {
        return isModerator;
    }

    /**
     * Метод принимает признак, является ли пользователь модератором для
     * гостевой книги
     *
     * @param isModerator признак
     */
    public void setModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }

    /**
     * Метод возвращает признак, является ли пользователь администратором
     *
     * @return признак
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Метод принимает признак, является ли пользователь администратором
     *
     * @param isAdmin признак
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return isModerator == login.isModerator &&
                isAdmin == login.isAdmin &&
                userName.equals(login.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, isModerator, isAdmin);
    }

    @Override
    public String toString() {
        return "Login{" +
                "userName='" + userName + '\'' +
                ", isModerator=" + isModerator +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
