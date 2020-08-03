package beans;

import dao.GuestbookDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import otherpack.Guestbook;

/**
 * JavaBean - обьект модели данных для гостевых книг
 *
 * @author Дикий Александр Николаевич
 * @version 1.1
 */
public class GuestbookBean {

    /**
     * Логирование класса GuestbookBean.class
     */
    public static final Logger log = Logger.getLogger(GuestbookBean.class);
    private ArrayList<Guestbook> guestbooks;
    private String nameGuestbook;
    private String description;
    private int displayOrder;
    private GuestbookDAO gDAO;

    /**
     * Экземпляр класса
     */
    public GuestbookBean() {
    }

    /**
     * Метод возращает список всех книг
     *
     * @return список всех книг
     */
    public ArrayList<Guestbook> getGuestbooks() {
        return guestbooks;
    }

    /**
     * Метод принимает список всех книг
     *
     * @param guestbooks - список всех книг
     */
    public void setGuestbooks(ArrayList<Guestbook> guestbooks) {
        this.guestbooks = guestbooks;
    }

    /**
     * Метод возращает имя книги
     *
     * @return имя книги
     */
    public String getNameGuestbook() {
        return nameGuestbook;
    }

    /**
     * Метод принимает имя книги
     *
     * @param nameGuestbook имя книги
     */
    public void setNameGuestbook(String nameGuestbook) {
        this.nameGuestbook = nameGuestbook;
    }

    /**
     * Метод возвращает описание книги
     *
     * @return описание книги
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод принимает описание книги
     *
     * @param description описание книги
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод возвращает порядок отображения
     *
     * @return порядок отображения
     */
    public int getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Метод принимает порядок отображения
     *
     * @param displayOrder порядок отображения
     */
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * Метод добавляет книгу в базу
     */
    public void addBook() {
        gDAO.addGuestbook(new Guestbook(
                nameGuestbook,
                description,
                displayOrder));
    }

    /**
     * Метод обновляет книгу в базе
     */
    public void updateBook() {
        gDAO.updateGuestbook(new Guestbook(
                nameGuestbook,
                description,
                displayOrder));
    }

    /**
     * Метод удаляет книгу из базы
     */
    public void deleteBook() {
        gDAO.deleteGuestbook(nameGuestbook);
    }

    /**
     * Метод возвращает книгу по имени из базы
     */
    public void getGuestbookByName() {
        Guestbook g = gDAO.getGuestbook(nameGuestbook);
        nameGuestbook = g.getName();
        description = g.getDescription();
        displayOrder = g.getDisplayOrder();
    }

    /**
     * Метод возвращает список книг полученых из базы
     */
    public void getListOfGuestbook() {
        guestbooks = gDAO.getGuestbookList();
    }

    /**
     * Метод принимает источник данных DataSource
     *
     * @param dataSource источник данных
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
