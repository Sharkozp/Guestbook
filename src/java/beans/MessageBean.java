package beans;

import dao.MessageDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import otherpack.Message;

/**
 * JavaBean - ������ ������ ������ ��� ���������
 *
 * @version 1.0
 * @author ����� ��������� ����������
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
     * ����������� ������ MessageBean.class
     */
    public static final Logger log = Logger.getLogger(MessageBean.class);

    /**
     * ��������� ������
     */
    public MessageBean() {
    }

    /**
     * ����� ���������� ���������� ��������� � �����
     *
     * @return ���������� ���������
     */
    public int getCount() {
        return count;
    }

    /**
     * ����� ��������� ���������� ��������� � �����
     *
     * @param count ���������� ���������
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * ����� ��������� id ���������
     *
     * @return id ���������
     */
    public int getId() {
        return id;
    }

    /**
     * ����� ��������� id ���������
     *
     * @param id id ���������
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * ����� ��������� ��� �������� �����
     *
     * @return ��� �������� �����
     */
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * ����� ��������� ��� �������� �����
     *
     * @param guestbookName ��� �������� �����
     */
    public void setGuestbookName(String guestbookName) {
        this.guestbookName = guestbookName;
    }

    /**
     * ����� ��������� ����� ���������
     *
     * @return ����� ���������
     */
    public String getMessage() {
        return message;
    }

    /**
     * ����� ��������� ����� ���������
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * ����� ��������� ������� ��������� ��� ����
     *
     * @return �������
     */
    public boolean isForAll() {
        return forAll;
    }

    /**
     * ����� ��������� ������� ��������� ��� ����
     *
     * @param forAll �������
     */
    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    /**
     * ����� ��������� ����� �������� ���������
     *
     * @return ����� �������� ���������
     */
    public Date getTimeCreation() {
        return timeCreation;
    }

    /**
     * ����� ��������� ����� �������� ���������
     *
     * @param timeCreation ����� �������� ���������
     */
    public void setTimeCreation(Date timeCreation) {
        this.timeCreation = timeCreation;
    }

    /**
     * ����� ��������� �������, ��� ��������� ����� ��� ����������
     *
     * @return �������
     */
    public boolean isNew4Admin() {
        return new4Admin;
    }

    /**
     * ����� ��������� �������, ��� ��������� ����� ��� ����������
     *
     * @param new4Admin �������
     */
    public void setNew4Admin(boolean new4Admin) {
        this.new4Admin = new4Admin;
    }

    /**
     * ����� ��������� ��� ������ ���������
     *
     * @return ��� ������ ���������
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * ����� ��������� ��� ������ ���������
     *
     * @param authorName ��� ������ ���������
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * ����� ��������� ip-����� ������ ���������
     *
     * @return ip-����� ������ ���������
     */
    public String getAuthorIP() {
        return authorIP;
    }

    /**
     * ����� ��������� ip-����� ������ ���������
     *
     * @param authorIP ip-����� ������ ���������
     */
    public void setAuthorIP(String authorIP) {
        this.authorIP = authorIP;
    }

    /**
     * ����� ��������� ����� ��������
     *
     * @return ����� ��������
     */
    public String getPhone() {
        return phone;
    }

    /**
     * ����� ��������� ����� ��������
     *
     * @param phone ����� ��������
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * ����� ��������� email ������
     *
     * @return email ������
     */
    public String getEmail() {
        return email;
    }

    /**
     * ����� ��������� email ������
     *
     * @param email email ������
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * ����� ��������� icq-����� ������
     *
     * @return icq-����� ������
     */
    public String getIcq() {
        return icq;
    }

    /**
     * ����� ��������� icq-����� ������
     *
     * @param icq icq-����� ������
     */
    public void setIcq(String icq) {
        this.icq = icq;
    }

    /**
     * ����� ��������� ����� �� ���������
     *
     * @return ����� �� ���������
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * ����� ��������� ����� �� ���������
     *
     * @param answerText ����� �� ���������
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * ����� ��������� ��� ���������� ����������� �� ���������
     *
     * @return ��� ���������� ����������� �� ���������
     */
    public String getAnswerName() {
        return answerName;
    }

    /**
     * ����� ��������� ��� ���������� ����������� �� ���������
     *
     * @param answerName ��� ���������� ����������� �� ���������
     */
    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    /**
     * ����� ��������� ����� ������
     *
     * @return ����� ������
     */
    public Date getAnswerTime() {
        return answerTime;
    }

    /**
     * ����� ��������� ����� ������
     *
     * @param answerTime ����� ������
     */
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    /**
     * ����� ��������� ������ ���� ��������� �� �����
     *
     * @return ������ ���� ��������� �� �����
     */
    public ArrayList<Message> getListMessages() {
        return listMessages;
    }
	
	/**
     * ����� ��������� ������ ���� ��������� �� �����
	 *
     * @param listMessages ������ ���� ��������� �� �����
     */
    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }  

    /**
     * ����� ��������� ������ ���� ��������� ����� �� ����
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
     * ����� ��������� ����� ��������� � ����
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
     * ����� ��������� ����� �� ���������
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
     * ����� ���������� ���������� ��������� �� ����
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
     * ����� ������� ��������� � ����
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
     * ����� ��������� ��������� � ����
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
     * ����� ��������� ��������� �� ���� �� ID
	 *
     * @return ���������
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
     * ����� ��������� �������� ������ DataSource
     *
     * @param dataSource - �������� ������
     */
    public void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            log.error("DataSource is not set");
        } else {
            this.dataSource = dataSource;
        }
    }
}
