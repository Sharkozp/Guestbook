package om.dykyi.beans;

/**
 * JavaBean - обьект модели данных для авторизации
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class Login {

    private String userName;
    private boolean isModerator;
    private boolean isAdmin;

    /**
     * Экземпляр класса
     */
    public Login() {
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
}
