package om.dykyi.beans;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean - обьект модели данных для сообщений
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class Message implements Serializable {
    private int id;
    private String message;
    private boolean forAll;
    private Date timeCreation;
    private boolean new4Admin;
    private String authorName;
    private String authorIP;
    private String phone;
    private String email;
    private int guestbookId;
    private Answer answer;

    public Message() {
    }

    public Message(String message, boolean forAll, Date timeCreation, String authorName, String authorIP, String phone, String email, int guestbookId) {
        this.message = message;
        this.forAll = forAll;
        this.timeCreation = timeCreation;
        this.authorName = authorName;
        this.authorIP = authorIP;
        this.phone = phone;
        this.email = email;
        this.guestbookId = guestbookId;
    }

    public Message(int id, String message, boolean forAll, Date timeCreation, boolean new4Admin, String authorName, String authorIP, String phone, String email, int guestbookId, Answer answer) {
        this.id = id;
        this.message = message;
        this.forAll = forAll;
        this.timeCreation = timeCreation;
        this.new4Admin = new4Admin;
        this.authorName = authorName;
        this.authorIP = authorIP;
        this.phone = phone;
        this.email = email;
        this.guestbookId = guestbookId;
        this.answer = answer;
    }

    /**
     * Метод возращает id сообщения
     *
     * @return id сообщения
     */
    public int getId() {
        return id;
    }

    /**
     * Метод принимает id сообщения
     *
     * @param id id сообщения
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возращает текст сообщения
     *
     * @return текст сообщения
     */
    public String getMessage() {
        return message;
    }

    /**
     * Метод принимает текст сообщения
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Метод возращает признак сообщения для всех
     *
     * @return признак
     */
    public boolean isForAll() {
        return forAll;
    }

    /**
     * Метод принимает признак сообщения для всех
     *
     * @param forAll признак
     */
    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    /**
     * Метод возращает время создания сообщения
     *
     * @return время создания сообщения
     */
    public Date getTimeCreation() {
        return timeCreation;
    }

    /**
     * Метод принимает время создания сообщения
     *
     * @param timeCreation время создания сообщения
     */
    public void setTimeCreation(Date timeCreation) {
        this.timeCreation = timeCreation;
    }

    /**
     * Метод возращает признак, что сообщение новое для модератора
     *
     * @return признак
     */
    public boolean isNew4Admin() {
        return new4Admin;
    }

    /**
     * Метод принимает признак, что сообщение новое для модератора
     *
     * @param new4Admin признак
     */
    public void setNew4Admin(boolean new4Admin) {
        this.new4Admin = new4Admin;
    }

    /**
     * Метод возращает имя автора сообщения
     *
     * @return author name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Метод принимает имя автора сообщения
     *
     * @param authorName author name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Метод возращает ip-адрес автора сообщения
     *
     * @return ip-адрес автора сообщения
     */
    public String getAuthorIP() {
        return authorIP;
    }

    /**
     * Метод принимает ip-адрес автора сообщения
     *
     * @param authorIP ip-адрес автора сообщения
     */
    public void setAuthorIP(String authorIP) {
        this.authorIP = authorIP;
    }

    /**
     * Метод возращает номер телефона
     *
     * @return номер телефона
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Метод принимает номер телефона
     *
     * @param phone номер телефона
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Метод возращает email автора
     *
     * @return email автора
     */
    public String getEmail() {
        return email;
    }

    /**
     * Метод принимает email автора
     *
     * @param email email автора
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Метод возращает имя гостевой книги
     *
     * @return имя гостевой книги
     */
    public int getGuestbookId() {
        return guestbookId;
    }

    /**
     * Метод принимает имя гостевой книги
     *
     * @param guestbookId guestbook ID
     */
    public void setGuestbookId(int guestbookId) {
        this.guestbookId = guestbookId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id &&
                forAll == message1.forAll &&
                new4Admin == message1.new4Admin &&
                guestbookId == message1.guestbookId &&
                Objects.equals(message, message1.message) &&
                Objects.equals(timeCreation, message1.timeCreation) &&
                Objects.equals(authorName, message1.authorName) &&
                Objects.equals(authorIP, message1.authorIP) &&
                Objects.equals(phone, message1.phone) &&
                Objects.equals(email, message1.email) &&
                Objects.equals(answer, message1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, forAll, timeCreation, new4Admin, authorName, authorIP, phone, email, guestbookId, answer);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", forAll=" + forAll +
                ", timeCreation=" + timeCreation +
                ", new4Admin=" + new4Admin +
                ", authorName='" + authorName + '\'' +
                ", authorIP='" + authorIP + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", guestbookId=" + guestbookId +
                ", answer=" + answer +
                '}';
    }
}
