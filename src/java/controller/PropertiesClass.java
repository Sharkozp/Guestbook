package controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс для получения свойств из файла. Реализация паттерна Singleton.
 *
 * @author Дикий Александр Николаевич
 * @version 1.1
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
     * Экземпляр класса
     */
    private PropertiesClass() {
    }

    /**
     * Статический метод возвращает единственный экземпляр данного класса
     *
     * @return единственный экземпляр данного класса
     */
    public static PropertiesClass getInstance() {
        return instance;
    }

    /**
     * Метод возвращает все параметры из файла
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
     * Метод возвращает JDBC-драйвер базы данных
     *
     * @return JDBC-драйвер
     */
    public String getJDBC_driver() {
        return JDBC_driver;
    }

    /**
     * Метод возвращает URL, где расположена база данных
     *
     * @return URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * Метод возвращает пароль в базе данных
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Метод возвращает учетное имя в базе данных
     *
     * @return учетное имя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод возвращает текущую локаль
     *
     * @return текущую локаль
     */
    public Locale getLocale() {
        return locale;
    }
}
