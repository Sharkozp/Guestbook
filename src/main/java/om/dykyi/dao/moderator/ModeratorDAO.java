package om.dykyi.dao.moderator;

public interface ModeratorDAO {
    public boolean isModerator(int userId, int guestbookId);
}
