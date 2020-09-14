package om.dykyi.action;

import om.dykyi.beans.*;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.guestbook.GuestbookDAO;
import om.dykyi.dao.message.MessageDAO;
import om.dykyi.dao.moderator.ModeratorDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * GuestbookAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию и загрузку всех сообщений и ответов выбраной книги или по
 * умолчанию на странице guestbook.jsp.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class GuestbookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию и загрузку всех сообщений и ответов
     * выбраной книги или по умолчанию на странице guestbook.jsp.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        MysqlDAOFactory daoFactory = (MysqlDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        GuestbookDAO guestbookDAO = daoFactory.getGuestbookDao();
        List<Guestbook> guestbookList = guestbookDAO.getGuestbookList();

        session.setAttribute("guestbookList", guestbookList);

        int guestbookId;
        if (request.getParameter("guestbookId") == null) {
            Guestbook guestbook = guestbookList.get(0);
            guestbookId = guestbook.getId();
        } else {
            guestbookId = Integer.parseInt(request.getParameter("guestbookId"));
        }
        session.setAttribute("guestbookId", guestbookId);

        boolean isModerator = false;
        User user = (User) session.getAttribute("user");
        if (user != null) {
            ModeratorDAO moderatorDAO = daoFactory.getModeratorDao();
            if (moderatorDAO.isModerator(user.getId(), guestbookId)) {
                isModerator = true;
            }
        }
        session.setAttribute("moderator", isModerator);

        MessageDAO messageDAO = daoFactory.getMessageDao();
        List<Message> messageList = messageDAO.getMessageList(guestbookId);
        session.setAttribute("messageList", messageList);

        session.setAttribute("messageCount", messageList.size());

        return getPage(request);
    }
}
