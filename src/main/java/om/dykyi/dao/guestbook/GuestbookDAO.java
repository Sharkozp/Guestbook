package om.dykyi.dao.guestbook;

import om.dykyi.beans.Guestbook;
import java.util.List;

public interface GuestbookDAO {
    public int addGuestbook(Guestbook guestbook);
    public Guestbook getGuestbook(int guestbookId);
    public boolean deleteGuestbook(int guestbookId);
    public Guestbook findGuestbook(Guestbook guestbook);
    public boolean updateGuestbook(Guestbook guestbook);

    public List<Guestbook> getGuestbookList();
}
