package controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ����� ��� ��������� ������� �� �����. ���������� �������� Singleton.
 *
 * @version 1.1
 * @author ����� ��������� ����������
 */
public class PropertiesClass {

    private static final PropertiesClass instance = new PropertiesClass();
    private static String propFile = "config";
    private String JDBC_driver;
    private String URL;
    private String username;
    private String password;
    private Locale locale;

    /**
     * ��������� ������
     */
    private PropertiesClass() {
    }

    /**
     * ����������� ����� ���������� ������������ ��������� ������� ������
     *
     * @return ������������ ��������� ������� ������
     */
    public static PropertiesClass getInstance() {
        return instance;
    }

    /**
     * ����� ���������� ��� ��������� �� �����
     */
    protected void getDbConfig() {
        ResourceBundle db = ResourceBundle.getBundle(propFile);
        locale = db.getLocale();
        JDBC_driver = db.getString("db.driver.class");
        URL = db.getString("db.connection.url");
        username = db.getString("db.username");
        password = db.getString("db.password");
    }

    /**
     * ����� ���������� JDBC-������� ���� ������
     * @return JDBC-�������
     */
    public String getJDBC_driver() {
        return JDBC_driver;
    }

    /**
     * ����� ���������� URL, ��� ����������� ���� ������
     * @return URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * ����� ���������� ������ � ���� ������
     * @return ������
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����� ���������� ������� ��� � ���� ������
     * @return ������� ���
     */
    public String getUsername() {
        return username;
    }

    /**
     * ����� ���������� ������� ������
     * @return ������� ������
     */
    public Locale getLocale() {
        return locale;
    }
}
