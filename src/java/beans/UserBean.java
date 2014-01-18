package beans;

import dao.UserDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import otherpack.User;
import org.apache.log4j.Logger;

/**
 * JavaBean - ������ ������ ������ ��� ������������
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class UserBean {

    private String userName;
    private String pwdDigest;
    private String lastName;
    private String firstName;
    private ArrayList<User> list;
    private boolean error;
    private DataSource dataSource;
    /**
     * ����������� ������ UserBean.class
     */
    public static final Logger log = Logger.getLogger(UserBean.class);

    /**
     * ��������� ������
     */
    public UserBean() {
    }

    /**
     * ����� ���������� ������ �������������
     *
     * @return ������ �������������
     */
    public ArrayList<User> getList() {
        return list;
    }

    /**
     * ����� ��������� ������ �������������
     *
     * @param list ������ �������������
     */
    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    /**
     * ����� ���������� ������� ���
     *
     * @return ������� ���
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ����� ��������� ������� ���
     *
     * @param userName ������� ���
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * ����� ���������� �������� ������
     *
     * @return �������� ������
     */
    public String getPwdDigest() {
        return pwdDigest;
    }

    /**
     * ����� ���������� ������� ������������
     *
     * @return ������� ������������
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * ����� ��������� ������� ������������
     *
     * @param lastName ������� ������������
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * ����� ��������� ��� ������������
     *
     * @param firstName ��� ������������
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * ����� ���������� ������
     *
     * @return ������
     */
    public boolean isError() {
        return error;
    }

    /**
     * ����� ��������� ������
     *
     * @param error ������
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * ����� ��������� ������ ��� �������� ��������� ������
     *
     * @param password ������
     * @throws NoSuchAlgorithmException
     */
    public void setPwdDigest(String password) throws NoSuchAlgorithmException {
        pwdDigest = new String();
        byte[] pwdByteArray;
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        pwdByteArray = md.digest();
        for (byte b : pwdByteArray) {
            pwdDigest += (new Byte(b)).intValue();
        }
    }

    /**
     * ����� ��������� ���������� �� ������������ � ���� ������
     *
     * @return �������
     */
    public boolean isUserExist() {
        String user = new String();
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            user = uDAO.getUser(userName, pwdDigest);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        if (user == (null) || user.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * ����� ��������� �������� ������������ ���������������
     *
     * @return �������
     */
    public boolean isAdmin() {
        boolean isAdmin = false;
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            isAdmin = uDAO.isAdmin(userName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        return isAdmin;
    }

    /**
     * ����� ���������� ������ ���� �������������
     */
    public void getListOfUsers() {
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            list = uDAO.getListOfUsers();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� �������� �� ���� ������ � ������������
     */
    public void getUser() {
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            User u = uDAO.getUser(userName);
            lastName = u.getLastName();
            firstName = u.getFirstName();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ������� ������������ �� ����
     */
    public void deleteUser() {
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            uDAO.deleteUser(userName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ������� ������������ � ����
     */
    public void addUser() {
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            uDAO.addUser(new User(
                    userName,
                    lastName,
                    firstName), pwdDigest);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ��������� ������������ � ����
     */
    public void updateUser() {
        try {
            UserDAO uDAO = new UserDAO(dataSource);
            uDAO.updateUser(userName, lastName, firstName);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
    }

    /**
     * ����� ��������� �������� ������ DataSource
     *
     * @param dataSource �������� ������
     */
    public void setDataSource(DataSource dataSource) {
        if (dataSource == null) {
            log.error("DataSource is not set");
        } else {
            this.dataSource = dataSource;
        }
    }
}
