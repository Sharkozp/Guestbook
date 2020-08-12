package om.dykyi.dao.guestbook;

import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.models.Guestbook;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public interface GuestbookDAO {
    /** Logging */
    public static final Logger LOGGER = Logger.getLogger(GuestbookDAO.class);
    public int addGuestbook(Guestbook guestbook);
    public Guestbook getGuestbook(int guestbookId);
    public boolean deleteGuestbook(int guestbookId);
    public Guestbook findGuestbook(Guestbook guestbook);
    public boolean updateGuestbook(Guestbook guestbook);

    public List<Guestbook> getGuestbookList();
}
