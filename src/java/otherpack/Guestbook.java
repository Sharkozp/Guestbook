package otherpack;

/**
 * Класс хранит настройки гостевой книги.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class Guestbook {

    private String name;
    private String description;
    private int displayOrder;

    /**
     * Экземпляр класса
     *
     * @param name         имя книги
     * @param description  описание книги
     * @param displayOrder порядок отображения
     */
    public Guestbook(String name, String description, int displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
    }

    /**
     * Метод возвращает имя гостевой книги
     *
     * @return имя гостевой книги
     */
    public String getName() {
        return name;
    }

    /**
     * Метод возвращает описание гостевой книги
     *
     * @return описание гостевой книги
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод возвращает порядок отображения гостевой книги
     *
     * @return порядок отображения гостевой книги
     */
    public int getDisplayOrder() {
        return displayOrder;
    }
}
