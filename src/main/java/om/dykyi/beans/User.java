package om.dykyi.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean - обьект модели данных для пользователя
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String pwdDigest;
    private String lastName;
    private String firstName;
    private boolean isAdmin;

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Constructor
     */
    public User(String username, String lastName, String firstName, boolean isAdmin) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.isAdmin = isAdmin;
    }

    /**
     * Constructor
     */
    public User(int id, String username, String lastName, String firstName, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.isAdmin = isAdmin;
    }

    /**
     * Constructor
     */
    public User(String username, String pwdDigest, String lastName, String firstName, boolean isAdmin) {
        this.username = username;
        this.pwdDigest = pwdDigest;
        this.lastName = lastName;
        this.firstName = firstName;
        this.isAdmin = isAdmin;
    }

    /**
     * Get ID
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password digest
     *
     * @return pwdDigest
     */
    public String getPwdDigest() {
        return pwdDigest;
    }

    /**
     * Set password digest
     */
    public void setPwdDigest(String pwdDigest) {
        this.pwdDigest = pwdDigest;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isAdmin == user.isAdmin &&
                Objects.equals(username, user.username) &&
                Objects.equals(pwdDigest, user.pwdDigest) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, pwdDigest, lastName, firstName, isAdmin);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwdDigest='" + pwdDigest + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
