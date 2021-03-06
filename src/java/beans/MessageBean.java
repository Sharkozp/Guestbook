package beans;

import dao.MessageDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import otherpack.Message;

/**
 * JavaBean - обьект модели данных для сообщений
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class MessageBean {

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
    private DataSource dataSource;
    /**
     * Логирование класса MessageBean.class
     */
    public static final Logger log = Logger.getLogger(MessageBean.class);

    /**
     * Экземпляр класса
     */
    public MessageBean() {
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
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            listMessages = mDAO.getMessageList(guestbookName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод добавляет новое сообщение в базу
     */
    public void addMessage() {
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            mDAO.addMessage(new Message(
                    guestbookName,
                    message,
                    forAll,
                    timeCreation,
                    authorName,
                    authorIP,
                    phone,
                    email,
                    icq));
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод добавляет ответ на сообщение
     */
    public void setAnswer() {
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            mDAO.setAnswer(answerText, answerName, answerTime, id);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод возвращает количество сообщений из базы
     */
    public void getMessageCount() {
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            count = mDAO.getMessageCount(guestbookName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод удаляет сообщение в базе
     */
    public void deleteMessage() {
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            mDAO.deleteMessage(id);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод обновляет сообщение в базе
     */
    public void updateMessage() {
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            mDAO.updateMessage(message, forAll, id);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * Метод возращает сообщение из базы по ID
	 *
     * @return сообщение
     */
    public String getMessageById() {
        message = null;
        try {
            MessageDAO mDAO = new MessageDAO(dataSource);
            message = mDAO.getMessage(id);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return message;
    }

    /**
     * Метод принимает источник данных DataSource
     *
     * @param dataSource - источник данных
     */
    public void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            log.error("DataSource is not set");
        } else {
            this.dataSource = dataSource;
        }
    }
}
