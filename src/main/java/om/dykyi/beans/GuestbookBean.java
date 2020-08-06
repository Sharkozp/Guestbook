package om.dykyi.beans;

import om.dykyi.models.GuestbookModel;
import org.apache.log4j.Logger;
import om.dykyi.otherpack.Guestbook;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private String guestbookName;
    private String description;
    private int displayOrder;
    private GuestbookModel guestbookModel;

    /**
     * Экземпляр класса
     */
    public GuestbookBean() {
        guestbookModel = new GuestbookModel();
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
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * Метод принимает имя книги
     *
     * @param guestbookName имя книги
     */
    public void setGuestbookName(String guestbookName) {
        this.guestbookName = guestbookName;
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
        guestbookModel.addGuestbook(new Guestbook(
                guestbookName,
                description,
                displayOrder));
    }

    /**
     * Метод обновляет книгу в базе
     */
    public void updateBook() {
        guestbookModel.updateGuestbook(new Guestbook(
                guestbookName,
                description,
                displayOrder));
    }

    /**
     * Метод удаляет книгу из базы
     */
    public void deleteBook() {
        guestbookModel.deleteGuestbook(guestbookName);
    }

    /**
     * Метод возвращает книгу по имени из базы
     */
    public void getGuestbookByName() {
        Guestbook g = guestbookModel.getGuestbook(guestbookName);
        guestbookName = g.getName();
        description = g.getDescription();
        displayOrder = g.getDisplayOrder();
    }

    /**
     * Метод возвращает список книг полученых из базы
     */
    public void getGuestbookList() {
        guestbooks = guestbookModel.getGuestbookList();
    }
}
