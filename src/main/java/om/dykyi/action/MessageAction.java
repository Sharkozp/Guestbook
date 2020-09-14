package om.dykyi.action;

import om.dykyi.beans.Guestbook;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.guestbook.GuestbookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * MessageAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы message.jsp.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class MessageAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы message.jsp.
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

        return getPage(request);
    }
}
