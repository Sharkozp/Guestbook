package otherpack;

/**
 * Класс хранит настройки пользователя.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class User {

    private String userName;
    private String lastName;
    private String firstName;

    /**
     * Экземпляр класса
     *
     * @param userName учетное имя пользователя
     * @param lastName фамилия пользователя
     * @param firstName имя пользователя
     */
    public User(String userName, String lastName, String firstName) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
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
}
