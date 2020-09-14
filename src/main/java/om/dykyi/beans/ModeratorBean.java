package om.dykyi.beans;

import om.dykyi.dao.moderator.MysqlModeratorDAO;
import org.apache.log4j.Logger;
import om.dykyi.models.Moderator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JavaBean - обьект модели данных для модераторов
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class ModeratorBean implements Serializable {
    private int id;
    private int userId;
    private List<Integer> guestBooksIds;

    public ModeratorBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getGuestBooksIds() {
        return guestBooksIds;
    }

    public void setGuestBooksIds(List<Integer> guestBooksIds) {
        this.guestBooksIds = guestBooksIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModeratorBean that = (ModeratorBean) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(guestBooksIds, that.guestBooksIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, guestBooksIds);
    }

    @Override
    public String toString() {
        return "ModeratorBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", guestBooksIds=" + guestBooksIds +
                '}';
    }
}
