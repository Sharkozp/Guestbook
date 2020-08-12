package om.dykyi.action;

import om.dykyi.beans.GuestbookBean;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.guestbook.GuestbookDAO;
import om.dykyi.models.Guestbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;

/**
 * MessageAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы message.jsp.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
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
        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();

        GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
        if (gBean == null) {
            gBean = new GuestbookBean();
        }
        MysqlDAOFactory daoFactory = (MysqlDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        GuestbookDAO guestbookDAO = daoFactory.getGuestbookDao();
        List<Guestbook> guestbookList = guestbookDAO.getGuestbookList();
        gBean.setGuestbooks(guestbookList);
        session.setAttribute("gBean", gBean);
        session.removeAttribute("mBean");

        return page + ".jsp";
    }
}
