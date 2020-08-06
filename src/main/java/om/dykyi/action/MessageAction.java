package om.dykyi.action;

import om.dykyi.beans.GuestbookBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();

        GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
        if (gBean == null) {
            gBean = new GuestbookBean();
        }
        gBean.getGuestbookList();
        session.setAttribute("gBean", gBean);
        session.removeAttribute("mBean");

        return page + ".jsp";
    }
}
