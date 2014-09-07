package otherpack;

/**
 * Класс хранит настройки модератора.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class Moderator {

    private int id;
    private String guestbookName;
    private String moderator;

    /**
     * Экземпляр класса
     *
     * @param id
     * @param guestbookName имя гостевой книги
     * @param moderator учетное имя модератора
     */
    public Moderator(int id, String guestbookName, String moderator) {
        this.id = id;
        this.guestbookName = guestbookName;
        this.moderator = moderator;
    }

    /**
     * Метод возвращает id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Метод возвращает имя гостевой книги
     *
     * @return имя гостевой книги
     */
    public String getGuestbookName() {
        return guestbookName;
    }

    /**
     * Метод возвращает учетное имя модератора
     *
     * @return учетное имя модератора
     */
    public String getModerator() {
        return moderator;
    }
}
