package otherpack;

import java.util.Date;

/**
 * Класс хранит настройки сообщения.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class Message {

    private int msgID;
    private String guestbookName;
    private String messageText;
    private boolean isForAll;
    private boolean isNewForAdmin;
    private Date creationTime;
    private String creatorName;
    private String creatorIP;
    private String phone;
    private String email;
    private String icq;
    private String answerText;
    private String answerName;
    private Date answerTime;

    /**
     * Экземпляр класса без ID сообщения и isNewForAdmin
     *
     * @param guestbookName имя гостевой книги
     * @param messageText   сообщение гостевой книги
     * @param isForAll      сообщение для всех
     * @param creationTime  дата создания сообщения
     * @param creatorName   имя создавшего сообщение
     * @param creatorIP     ip адрес создавшего сообщения
     * @param phone         номер телефона
     * @param email         email адрес
     * @param icq           icq номер
     */
    public Message(String guestbookName, String messageText, boolean isForAll, Date creationTime, String creatorName, String creatorIP, String phone, String email, String icq) {
        this.guestbookName = guestbookName;
        this.messageText = messageText;
        this.isForAll = isForAll;
        this.creationTime = creationTime;
        this.creatorName = creatorName;
        this.creatorIP = creatorIP;
        this.phone = phone;
        this.email = email;
        this.icq = icq;
    }

    /**
     * Экземпляр класса с ID сообщения и isNewForAdmin
     *
     * @param msgID         id сообщения
     * @param guestbookName имя гостевой книги
     * @param messageText   сообщение гостевой книги
     * @param isForAll      сообщение для всех
     * @param isNewForAdmin сообщение новое для модератора
     * @param creationTime  дата создания сообщения
     * @param creatorName   имя создавшего сообщение
     * @param creatorIP     ip адрес создавшего сообщения
     * @param phone         номер телефона
     * @param email         email адрес
     * @param icq           icq номер
     */
    public Message(int msgID, String guestbookName, String messageText, boolean isForAll, boolean isNewForAdmin, Date creationTime, String creatorName, String creatorIP, String phone, String email, String icq) {
        this.msgID = msgID;
        this.guestbookName = guestbookName;
        this.messageText = messageText;
        this.isForAll = isForAll;
        this.isNewForAdmin = isNewForAdmin;
        this.creationTime = creationTime;
        this.creatorName = creatorName;
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
    public int getMsgID() {
        return msgID;
    }

    /**
     * Метод возвращает имя гостевой книги
     *
     * @return имя гостевой книги
     */
    public String getGuestbookName() {
        return guestbookName;
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
    public boolean isIsForAll() {
        return isForAll;
    }

    /**
     * Метод возвращает признак, что сообщение новое для модератора
     *
     * @return признак, что сообщение новое для модератора
     */
    public boolean isIsNewForAdmin() {
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
    public String getCreatorName() {
        return creatorName;
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
