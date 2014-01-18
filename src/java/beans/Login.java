package beans;

/**
 * JavaBean - ������ ������ ������ ��� �����������
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class Login {

    private String userName;
    private boolean isModerator;
    private boolean isAdmin;

    /**
     * ��������� ������
     */
    public Login() {
    }

    /**
     * ����� ���������� �������, �������� �� ������������ ����������� ���
     * �������� �����
     *
     * @return �������
     */
    public boolean isModerator() {
        return isModerator;
    }

    /**
     * ����� ��������� �������, �������� �� ������������ ����������� ���
     * �������� �����
     *
     * @param isModerator �������
     */
    public void setModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }

    /**
     * ����� ���������� �������, �������� �� ������������ ���������������
     *
     * @return �������
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * ����� ��������� �������, �������� �� ������������ ���������������
     *
     * @param isAdmin �������
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * ����� ���������� ������� ��� ������������
     *
     * @return ������� ���
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ����� ��������� ������� ��� ������������
     *
     * @param userName ������� ���
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
