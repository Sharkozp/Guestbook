package beans;

import dao.ModeratorDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import otherpack.Moderator;
import org.apache.log4j.Logger;

/**
 * JavaBean - ������ ������ ������ ��� �����������
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class ModeratorBean {

    private int id;
    private String username;
    private String guestbookName;
    private ArrayList<Moderator> listOfModeratorsBooks;
    private ModeratorDAO modDAO;
    /**
     * ����������� ������ ModeratorBean.class
     */
    public static final Logger log = Logger.getLogger(ModeratorBean.class);

    /**
     * ��������� ������
     */
    public ModeratorBean() {
    }

    /**
     * ����� ��������� ������ ���� �������� ���� ����������
     *
     * @return ������ �������� ����
     */
    public ArrayList<Moderator> getListOfModeratorsBooks() {
        return listOfModeratorsBooks;
    }

    /**
     * ����� ��������� ������ ���� �������� ���� ����������
     *
     * @param listOfModeratorsBooks ������ �������� ����
     */
    public void setListOfModeratorsBooks(ArrayList<Moderator> listOfModeratorsBooks) {
        this.listOfModeratorsBooks = listOfModeratorsBooks;
    }

    /**
     * ����� ��������� id
     * @return ��������� id
     */
    public int getId() {
        return id;
    }

    /**
     * ����� ��������� id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * ����� ��������� ������� ���
     * @return ������� ���
     */
    public String getUsername() {
        return username;
    }

    /**
     * ����� ��������� ������� ���
     * @param username ������� ���
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * ����� ��������� ��� �������� �����
     * @return ��� �������� �����
     */
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * ����� ��������� ��� �������� �����
     * @param guestbookName ��� �������� �����
     */
    public void setGuestbookName(String guestbookName) {
        this.guestbookName = guestbookName;
    }

    /**
     * ����� ��������� �� ���� ������ ���� ���� ����������
     */
    public void getListBooks() {
        listOfModeratorsBooks = modDAO.getListOfModeratorsBook(username);
    }

    /**
     * ����� ������� �� ���� �������� � ����� ���������� �� ID
     */
    public void deleteModeratorBook() {
        modDAO.deleteModeratorBook(id);
    }

    /**
     * ����� ���������, �������� �� ������������ ����������� �����
     * @return �������
     */
    public boolean isModerator() {
        return modDAO.isModerator(username, guestbookName);
    }

    /**
     * ����� ��������� ����������� �������� ����� 
     */
    public void setModeratorsBook() {
        modDAO.addModerator(username, guestbookName);
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
            try {
                modDAO = new ModeratorDAO(dataSource);
            } catch (SQLException se) {
                log.error(se.getMessage());
            }
        }
    }
}
