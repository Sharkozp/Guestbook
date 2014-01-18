package otherpack;

/**
 * ����� ������ ��������� ����������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class Moderator {

    private int id;
    private String guestbookName;
    private String moderator;

    /**
     * ��������� ������
     *
     * @param id
     * @param guestbookName ��� �������� �����
     * @param moderator ������� ��� ����������
     */
    public Moderator(int id, String guestbookName, String moderator) {
        this.id = id;
        this.guestbookName = guestbookName;
        this.moderator = moderator;
    }

    /**
     * ����� ���������� id
     *
     * @return id
     */
    public int getId() {
        return id;
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
     * ����� ���������� ������� ��� ����������
     *
     * @return ������� ��� ����������
     */
    public String getModerator() {
        return moderator;
    }
}
