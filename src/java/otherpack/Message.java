package otherpack;

import java.util.Date;

/**
 * ����� ������ ��������� ���������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
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
     * ��������� ������ ��� ID ��������� � isNewForAdmin
     *
     * @param guestbookName ��� �������� �����
     * @param messageText ��������� �������� �����
     * @param isForAll ��������� ��� ����
     * @param creationTime ���� �������� ���������
     * @param creatorName ��� ���������� ���������
     * @param creatorIP ip ����� ���������� ���������
     * @param phone ����� ��������
     * @param email email �����
     * @param icq icq �����
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
     * ��������� ������ � ID ��������� � isNewForAdmin
     *
     * @param msgID id ���������
     * @param guestbookName ��� �������� �����
     * @param messageText ��������� �������� �����
     * @param isForAll ��������� ��� ����
     * @param isNewForAdmin ��������� ����� ��� ����������
     * @param creationTime ���� �������� ���������
     * @param creatorName ��� ���������� ���������
     * @param creatorIP ip ����� ���������� ���������
     * @param phone ����� ��������
     * @param email email �����
     * @param icq icq �����
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
     * ����� ���������� id ���������
     *
     * @return id ���������
     */
    public int getMsgID() {
        return msgID;
    }

    /**
     * ����� ���������� ��� �������� �����
     *
     * @return ��� �������� �����
     */
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * ����� ���������� ��������� �������� �����
     *
     * @return ��������� �������� �����
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * ����� ���������� �������, ��� ��������� ��� ����
     *
     * @return �������, ��� ��������� ��� ����
     */
    public boolean isIsForAll() {
        return isForAll;
    }

    /**
     * ����� ���������� �������, ��� ��������� ����� ��� ����������
     *
     * @return �������, ��� ��������� ����� ��� ����������
     */
    public boolean isIsNewForAdmin() {
        return isNewForAdmin;
    }

    /**
     * ����� ���������� ���� �������� ���������
     *
     * @return ���� �������� ���������
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * ����� ���������� ��� ���������� ���������
     *
     * @return ��� ���������� ���������
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * ����� ���������� ip ����� ���������� ���������
     *
     * @return ip ����� ���������� ���������
     */
    public String getCreatorIP() {
        return creatorIP;
    }

    /**
     * ����� ���������� ����� ��������
     *
     * @return ����� ��������
     */
    public String getPhone() {
        return phone;
    }

    /**
     * ����� ���������� email �����
     *
     * @return email �����
     */
    public String getEmail() {
        return email;
    }

    /**
     * ����� ���������� icq �����
     *
     * @return icq �����
     */
    public String getIcq() {
        return icq;
    }

    /**
     * ����� ��������� ����� �� ���������
     *
     * @param answerText ����� �� ���������
     * @param answerName ��� ����������� �� ���������
     * @param answerTime ����� ������ �� ���������
     */
    public void setAnswer(String answerText, String answerName, Date answerTime) {
        this.answerText = answerText;
        this.answerName = answerName;
        this.answerTime = answerTime;
    }

    /**
     * ����� ���������� ����� �� ���������
     *
     * @return ����� �� ���������
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * ����� ���������� ��� ����������� �� ���������
     *
     * @return ��� ����������� �� ���������
     */
    public String getAnswerName() {
        return answerName;
    }

    /**
     * ����� ���������� ����� ������ �� ���������
     *
     * @return ����� ������ �� ���������
     */
    public Date getAnswerTime() {
        return answerTime;
    }
}
