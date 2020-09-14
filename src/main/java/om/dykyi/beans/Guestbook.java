package om.dykyi.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean - обьект модели данных для гостевых книг
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class Guestbook implements Serializable {
    private int id;
    private String name;
    private String description;
    private int displayOrder;

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param description
     * @param displayOrder
     */
    public Guestbook(int id, String name, String description, int displayOrder) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
    }

    /**
     * Get ID
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id guestbook id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возращает имя книги
     *
     * @return имя книги
     */
    public String getName() {
        return name;
    }

    /**
     * Метод принимает имя книги
     *
     * @param name имя книги
     */
    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guestbook that = (Guestbook) o;
        return id == that.id &&
                displayOrder == that.displayOrder &&
                name.equals(that.name) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, displayOrder);
    }

    @Override
    public String toString() {
        return "GuestbookBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", displayOrder=" + displayOrder +
                '}';
    }
}
