package om.dykyi.controller;

import java.util.ResourceBundle;

/**
 * Класс для получения свойств из файла. Реализация паттерна Singleton.
 *
 * @author Дикий Александр Николаевич
 * @version 1.1
 */
public class PropertiesClass {

    private static final PropertiesClass instance = new PropertiesClass();
    private static final String PROP_FILE = "config";
    private String jdbcDriver;
    private String URL;
    private String username;
    private String password;

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
        ResourceBundle db = ResourceBundle.getBundle(PROP_FILE);
        jdbcDriver = db.getString("db.driver.class");
        URL = db.getString("db.connection.url");
        username = db.getString("db.username");
        password = db.getString("db.password");
    }

    /**
     * Метод возвращает JDBC-драйвер базы данных
     *
     * @return JDBC-драйвер
     */
    public String getJdbcDriver() {
        return jdbcDriver;
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
     * Метод возвращает учетное имя в базе данных
     *
     * @return учетное имя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод возвращает пароль в базе данных
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }
}
