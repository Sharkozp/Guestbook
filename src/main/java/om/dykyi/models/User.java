package om.dykyi.models;

/**
 * Класс хранит настройки пользователя.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class User {

    private String userName;
    private String lastName;
    private String firstName;
    private boolean isAdmin;

    /**
     * Экземпляр класса
     *
     * @param userName  учетное имя пользователя
     * @param lastName  фамилия пользователя
     * @param firstName имя пользователя
     */
    public User(String userName, String lastName, String firstName, boolean isAdmin) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.isAdmin = isAdmin;
    }

    /**
     * Метод возвращает учетное имя пользователя
     *
     * @return учетное имя пользователя
     */
    public String getUserName() {
        return userName;
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
     * Метод возвращает фамилию пользователя
     *
     * @return фамилия пользователя
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Is user admin
     *
     * @return admin flag
     */
    public boolean isAdmin() {
        return isAdmin;
    }
}
