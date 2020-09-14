package om.dykyi.action;

import om.dykyi.beans.Guestbook;
import om.dykyi.beans.Message;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.guestbook.GuestbookDAO;
import om.dykyi.dao.message.MessageDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * AddMessageAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * получение и проверку сообщения, обрезку до 4 байт.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class AddMessageAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и проверку сообщения, обрезку до 4 байт.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("guestbookList");

        MysqlDAOFactory daoFactory = (MysqlDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        MessageDAO messageDAO = daoFactory.getMessageDao();

        boolean isForAll = Boolean.parseBoolean(request.getParameter("forAll"));

        int guestbookId  = Integer.parseInt(request.getParameter("guestbookId"));

        Message message = new Message(
                request.getParameter("message"),
                isForAll,
                new Date(),
                request.getParameter("authorName"),
                request.getRemoteHost(),
                request.getParameter("phone"),
                request.getParameter("email"),
                guestbookId
        );

        messageDAO.addMessage(message);
        session.setAttribute("newMessage", message);

        return getPage(request);
    }
}
