package om.dykyi.beans;

import om.dykyi.models.MessageModel;
import org.apache.log4j.Logger;
import om.dykyi.otherpack.Message;

import java.util.ArrayList;
import java.util.Date;

/**
 * JavaBean - обьект модели данных для сообщений
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class MessageBean {
    private MessageModel messageModel;
    private int id;
    private String guestbookName;
    private String message;
    private boolean forAll;
    private Date timeCreation;
    private boolean new4Admin;
    private String authorName;
    private String authorIP;
    private String phone;
    private String email;
    private String icq;
    private String answerText;
    private String answerName;
    private Date answerTime;
    private int count;
    private ArrayList<Message> listMessages;

    /**
     * Логирование класса MessageBean.class
     */
    public static final Logger log = Logger.getLogger(MessageBean.class);

    /**
     * Экземпляр класса
     */
    public MessageBean() {
        messageModel = new MessageModel();
    }

    /**
     * Метод возвращает количество сообщений в книге
     *
     * @return количество сообщений
     */
    public int getCount() {
        return count;
    }

    /**
     * Метод принимает количество сообщений в книге
     *
     * @param count количество сообщений
     */
    public void setCount(int count) {
        this.count = count;
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
     * Метод возращает имя гостевой книги
     *
     * @return имя гостевой книги
     */
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * Метод принимает имя гостевой книги
     *
     * @param guestbookName имя гостевой книги
     */
    public void setGuestbookName(String guestbookName) {
        this.guestbookName = guestbookName;
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
     * @return имя автора сообщения
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Метод принимает имя автора сообщения
     *
     * @param authorName имя автора сообщения
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
     * Метод возращает icq-номер автора
     *
     * @return icq-номер автора
     */
    public String getIcq() {
        return icq;
    }

    /**
     * Метод принимает icq-номер автора
     *
     * @param icq icq-номер автора
     */
    public void setIcq(String icq) {
        this.icq = icq;
    }

    /**
     * Метод возращает ответ на сообщение
     *
     * @return ответ на сообщение
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * Метод принимает ответ на сообщение
     *
     * @param answerText ответ на сообщение
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * Метод возращает имя модератора ответившего на сообщение
     *
     * @return имя модератора ответившего на сообщение
     */
    public String getAnswerName() {
        return answerName;
    }

    /**
     * Метод принимает имя модератора ответившего на сообщение
     *
     * @param answerName имя модератора ответившего на сообщение
     */
    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    /**
     * Метод возращает время ответа
     *
     * @return время ответа
     */
    public Date getAnswerTime() {
        return answerTime;
    }

    /**
     * Метод принимает время ответа
     *
     * @param answerTime время ответа
     */
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    /**
     * Метод возращает список всех сообщений из книги
     *
     * @return список всех сообщений из книги
     */
    public ArrayList<Message> getListMessages() {
        return listMessages;
    }

    /**
     * Метод принимает список всех сообщений из книги
     *
     * @param listMessages список всех сообщений из книги
     */
    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

    /**
     * Метод принимает список всех сообщений книги из базы
     */
    public void getListOfMessages() {
        listMessages = messageModel.getMessageList(guestbookName);
    }

    /**
     * Метод добавляет новое сообщение в базу
     */
    public void addMessage() {
        messageModel.addMessage(new Message(
                guestbookName,
                message,
                forAll,
                timeCreation,
                authorName,
                authorIP,
                phone,
                email,
                icq));
    }

    /**
     * Метод добавляет ответ на сообщение
     */
    public void setAnswer() {
        messageModel.setAnswer(answerText, answerName, answerTime, id);
    }

    /**
     * Метод возвращает количество сообщений из базы
     */
    public void getMessageCount() {
        count = messageModel.getMessageCount(guestbookName);
    }

    /**
     * Метод удаляет сообщение в базе
     */
    public void deleteMessage() {
        messageModel.deleteMessage(id);
    }

    /**
     * Метод обновляет сообщение в базе
     */
    public void updateMessage() {
        messageModel.updateMessage(message, forAll, id);
    }

    /**
     * Метод возращает сообщение из базы по ID
     *
     * @return сообщение
     */
    public String getMessageById() {
        message = messageModel.getMessage(id);
        return message;
    }
}
