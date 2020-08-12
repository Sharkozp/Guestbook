package om.dykyi.beans;

import om.dykyi.dao.moderator.ModeratorModel;
import org.apache.log4j.Logger;
import om.dykyi.models.Moderator;

import java.util.ArrayList;

/**
 * JavaBean - обьект модели данных для модераторов
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class ModeratorBean {

    private int id;
    private String username;
    private String guestbookName;
    private ArrayList<Moderator> listOfModeratorsBooks;
    private ModeratorModel moderatorModel;
    /**
     * Логирование класса ModeratorBean.class
     */
    public static final Logger log = Logger.getLogger(ModeratorBean.class);

    /**
     * Экземпляр класса
     */
    public ModeratorBean() {
        moderatorModel = new ModeratorModel();
    }

    /**
     * Метод возращает список всех гостевых книг модератора
     *
     * @return список гостевых книг
     */
    public ArrayList<Moderator> getListOfModeratorsBooks() {
        return listOfModeratorsBooks;
    }

    /**
     * Метод принимает список всех гостевых книг модератора
     *
     * @param listOfModeratorsBooks список гостевых книг
     */
    public void setListOfModeratorsBooks(ArrayList<Moderator> listOfModeratorsBooks) {
        this.listOfModeratorsBooks = listOfModeratorsBooks;
    }

    /**
     * Метод возращает id
     *
     * @return возращает id
     */
    public int getId() {
        return id;
    }

    /**
     * Метод принимает id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возращает учетное имя
     *
     * @return учетное имя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод принимает учетное имя
     *
     * @param username учетное имя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод возращает имя гостевых книги
     *
     * @return имя гостевых книги
     */
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * Метод принимает имя гостевых книги
     *
     * @param guestbookName имя гостевых книги
     */
    public void setGuestbookName(String guestbookName) {
        this.guestbookName = guestbookName;
    }

    /**
     * Метод возращает из базы список всех книг модератора
     */
    public void getListBooks() {
        listOfModeratorsBooks = moderatorModel.getListOfModeratorsBook(username);
    }

    /**
     * Метод удаляет из базы привязку к книге модератора по ID
     */
    public void deleteModeratorBook() {
        moderatorModel.deleteModeratorBook(id);
    }

    /**
     * Метод проверяет, является ли пользователь модератором книги
     *
     * @return признак
     */
    public boolean isModerator() {
        return moderatorModel.isModerator(username, guestbookName);
    }

    /**
     * Метод назначает модератором гостевых книги
     */
    public void setModeratorsBook() {
        moderatorModel.addModerator(username, guestbookName);
    }
}
