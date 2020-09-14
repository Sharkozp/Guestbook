package om.dykyi.models;

import java.util.Date;

/**
 * Класс хранит настройки сообщения.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class MessageModel {

    private int id;
    private int guestbookId;
    private String messageText;
    private boolean isForAll;
    private boolean isNewForAdmin;
    private Date creationTime;
    private String authorName;
    private String creatorIP;
    private String phone;
    private String email;
    private String icq;
    private String answerText;
    private String answerName;
    private Date answerTime;

    /**
     * Экземпляр класса с ID сообщения и isNewForAdmin
     *
     * @param id         id сообщения
     * @param guestbookId имя гостевой книги
     * @param messageText   сообщение гостевой книги
     * @param isForAll      сообщение для всех
     * @param isNewForAdmin сообщение новое для модератора
     * @param creationTime  дата создания сообщения
     * @param authorName   имя создавшего сообщение
     * @param creatorIP     ip адрес создавшего сообщения
     * @param phone         номер телефона
     * @param email         email адрес
     * @param icq           icq номер
     */
    public MessageModel(int id, int guestbookId, String messageText, boolean isForAll, boolean isNewForAdmin, Date creationTime, String authorName, String creatorIP, String phone, String email, String icq) {
        this.id = id;
        this.guestbookId = guestbookId;
        this.messageText = messageText;
        this.isForAll = isForAll;
        this.isNewForAdmin = isNewForAdmin;
        this.creationTime = creationTime;
        this.authorName = authorName;
        this.creatorIP = creatorIP;
        this.phone = phone;
        this.email = email;
        this.icq = icq;
    }

    /**
     * Метод возвращает id сообщения
     *
     * @return id сообщения
     */
    public int getId() {
        return id;
    }

    /**
     * Метод возвращает имя гостевой книги
     *
     * @return имя гостевой книги
     */
    public int getGuestbookId() {
        return guestbookId;
    }

    /**
     * Метод возвращает сообщение гостевой книги
     *
     * @return сообщение гостевой книги
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Метод возвращает признак, что сообщение для всех
     *
     * @return признак, что сообщение для всех
     */
    public boolean isForAll() {
        return isForAll;
    }

    /**
     * Метод возвращает признак, что сообщение новое для модератора
     *
     * @return признак, что сообщение новое для модератора
     */
    public boolean isNewForAdmin() {
        return isNewForAdmin;
    }

    /**
     * Метод возвращает дату создания сообщения
     *
     * @return дата создания сообщения
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Метод возвращает имя создавшего сообщение
     *
     * @return имя создавшего сообщение
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Метод возвращает ip адрес создавшего сообщения
     *
     * @return ip адрес создавшего сообщения
     */
    public String getCreatorIP() {
        return creatorIP;
    }

    /**
     * Метод возвращает номер телефона
     *
     * @return номер телефона
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Метод возвращает email адрес
     *
     * @return email адрес
     */
    public String getEmail() {
        return email;
    }

    /**
     * Метод возвращает icq номер
     *
     * @return icq номер
     */
    public String getIcq() {
        return icq;
    }

    /**
     * Метод принимает ответ на сообщение
     *
     * @param answerText ответ на сообщение
     * @param answerName имя ответившего на сообщение
     * @param answerTime время ответа на сообщение
     */
    public void setAnswer(String answerText, String answerName, Date answerTime) {
        this.answerText = answerText;
        this.answerName = answerName;
        this.answerTime = answerTime;
    }

    /**
     * Метод возвращает ответ на сообщение
     *
     * @return ответ на сообщение
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * Метод возвращает имя ответившего на сообщение
     *
     * @return имя ответившего на сообщение
     */
    public String getAnswerName() {
        return answerName;
    }

    /**
     * Метод возвращает время ответа на сообщение
     *
     * @return время ответа на сообщение
     */
    public Date getAnswerTime() {
        return answerTime;
    }
}
