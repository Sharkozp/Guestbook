package controller;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * ����� ��� ����������. ���������� �������� Singleton.
 *
 * @version 1.0
 * @author ����� �.�.
 */
public class ConnectionPool {

    private DataSource dataSource;
    PropertiesClass propClass;
    private static final ConnectionPool instance = new ConnectionPool();
    /**
     * ����������� ������ ConnectionPool.class
     */
    public static final Logger log = Logger.getLogger(ConnectionPool.class);

    /**
     * ��������� ������
     */
    private ConnectionPool() {
    }

    /**
     * ����������� ����� ���������� ������������ ��������� ������� ������
     *
     * @return ������������ ��������� ������� ������
     */
    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * ����� ��������� ��� �������� �� ����� ��� �������� ���� ����������
     */
    public void setProperties() {
        propClass = PropertiesClass.getInstance();

        try {
            propClass.getDbConfig();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        dataSource = setupDataSource(propClass.getJDBC_driver(), propClass.getURL());
    }

    /**
     * ����� �������� ���� ����������
     *
     * @param dbDriver
     * @param dbURL
     * @return
     */
    public static DataSource setupDataSource(String dbDriver, String dbURL) {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(dbDriver);
        bds.setUrl(dbURL);
        return bds;
    }

    /**
     * ����� ��������� ��� ���������� � ����� ������
     *
     * @param dataSource
     * @throws SQLException
     */
    public static void shutdownDataSource(DataSource dataSource) throws SQLException {
        BasicDataSource bds = (BasicDataSource) dataSource;
        bds.close();
    }

    /**
     * ����� ���������� �������� ������
     *
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}
