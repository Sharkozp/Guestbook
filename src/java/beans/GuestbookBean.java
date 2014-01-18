package beans;

import dao.GuestbookDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import otherpack.Guestbook;

/**
 * JavaBean - ������ ������ ������ ��� �������� ����
 *
 * @version 1.1
 * @author ����� ��������� ����������
 */
public class GuestbookBean {

    /**
     * ����������� ������ GuestbookBean.class
     */
    public static final Logger log = Logger.getLogger(GuestbookBean.class);
    private ArrayList<Guestbook> guestbooks;
    private String nameGuestbook;
    private String description;
    private int displayOrder;
    private GuestbookDAO gDAO;

    /**
     * ��������� ������
     */
    public GuestbookBean() {
    }

    /**
     * ����� ��������� ������ ���� ����
     *
     * @return ������ ���� ����
     */
    public ArrayList<Guestbook> getGuestbooks() {
        return guestbooks;
    }

    /**
     * ����� ��������� ������ ���� ����
     *
     * @param guestbooks - ������ ���� ����
     */
    public void setGuestbooks(ArrayList<Guestbook> guestbooks) {
        this.guestbooks = guestbooks;
    }

    /**
     * ����� ��������� ��� �����
     *
     * @return ��� �����
     */
    public String getNameGuestbook() {
        return nameGuestbook;
    }

    /**
     * ����� ��������� ��� �����
     *
     * @param nameGuestbook ��� �����
     */
    public void setNameGuestbook(String nameGuestbook) {
        this.nameGuestbook = nameGuestbook;
    }

    /**
     * ����� ���������� �������� �����
     *
     * @return �������� �����
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����� ��������� �������� �����
     *
     * @param description �������� �����
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ����� ���������� ������� �����������
     *
     * @return ������� �����������
     */
    public int getDisplayOrder() {
        return displayOrder;
    }

    /**
     * ����� ��������� ������� �����������
     *
     * @param displayOrder ������� �����������
     */
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * ����� ��������� ����� � ����
     */
    public void addBook() {
        gDAO.addGuestbook(new Guestbook(
                nameGuestbook,
                description,
                displayOrder));
    }

    /**
     * ����� ��������� ����� � ����
     */
    public void updateBook() {
        gDAO.updateGuestbook(new Guestbook(
                nameGuestbook,
                description,
                displayOrder));
    }

    /**
     * ����� ������� ����� �� ����
     */
    public void deleteBook() {
        gDAO.deleteGuestbook(nameGuestbook);
    }

    /**
     * ����� ���������� ����� �� ����� �� ����
     */
    public void getGuestbookByName() {
        Guestbook g = gDAO.getGuestbook(nameGuestbook);
        nameGuestbook = g.getName();
        description = g.getDescription();
        displayOrder = g.getDisplayOrder();
    }

    /**
     * ����� ���������� ������ ���� ��������� �� ����
     */
    public void getListOfGuestbook() {
        guestbooks = gDAO.getGuestbookList();
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
                gDAO = new GuestbookDAO(dataSource);
            } catch (SQLException se) {
                log.error(se.getMessage());
            }
        }
    }
}
