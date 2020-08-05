package om.dykyi.beans;

import om.dykyi.dao.ModeratorDAO;
import org.apache.log4j.Logger;
import om.dykyi.otherpack.Moderator;

import javax.sql.DataSource;
import java.sql.SQLException;
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
    private ModeratorDAO modDAO;
    /**
     * Логирование класса ModeratorBean.class
     */
    public static final Logger log = Logger.getLogger(ModeratorBean.class);

    /**
     * Экземпляр класса
     */
    public ModeratorBean() {
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
        listOfModeratorsBooks = modDAO.getListOfModeratorsBook(username);
    }

    /**
     * Метод удаляет из базы привязку к книге модератора по ID
     */
    public void deleteModeratorBook() {
        modDAO.deleteModeratorBook(id);
    }

    /**
     * Метод проверяет, является ли пользователь модератором книги
     *
     * @return признак
     */
    public boolean isModerator() {
        return modDAO.isModerator(username, guestbookName);
    }

    /**
     * Метод назначает модератором гостевых книги
     */
    public void setModeratorsBook() {
        modDAO.addModerator(username, guestbookName);
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
                modDAO = new ModeratorDAO(dataSource);
            } catch (SQLException se) {
                log.error(se.getMessage());
            }
        }
    }
}
