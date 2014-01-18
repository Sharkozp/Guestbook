package otherpack;

/**
 * ����� ������ ��������� ������������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class User {

    private String userName;
    private String lastName;
    private String firstName;

    /**
     * ��������� ������
     *
     * @param userName ������� ��� ������������
     * @param lastName ������� ������������
     * @param firstName ��� ������������
     */
    public User(String userName, String lastName, String firstName) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * ����� ���������� ������� ��� ������������
     *
     * @return ������� ��� ������������
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ����� ���������� ��� ������������
     *
     * @return ��� ������������
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * ����� ���������� ������� ������������
     *
     * @return ������� ������������
     */
    public String getLastName() {
        return lastName;
    }
}
