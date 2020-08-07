package om.dykyi.action;

import om.dykyi.beans.GuestbookBean;
import om.dykyi.beans.Login;
import om.dykyi.beans.MessageBean;
import om.dykyi.beans.ModeratorBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * GuestbookAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию и загрузку всех сообщений и ответов выбраной книги или по
 * умолчанию на странице guestbook.jsp.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class GuestbookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию и загрузку всех сообщений и ответов
     * выбраной книги или по умолчанию на странице guestbook.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        session.removeAttribute("uBean");
        GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
        if (gBean == null) {
            gBean = new GuestbookBean();
        }
        String nameGB = request.getParameter("nameGuestbook");
        if (nameGB == null || nameGB.length() == 0) {
            // TODO change to get first item from DB
            gBean.getGuestbookList();
            nameGB = gBean.getGuestbooks().get(0).getName();
        }

        session.setAttribute("gBean", gBean);
        MessageBean mBean = (MessageBean) session.getAttribute("mBean");
        if (mBean == null) {
            mBean = new MessageBean();
        }
        mBean.setGuestbookName(nameGB);
        mBean.getListOfMessages();
        mBean.getMessageCount();
        session.setAttribute("mBean", mBean);

        Login login = (Login) session.getAttribute("login");
        if (login == null) {
            login = new Login();
        }
        ModeratorBean modBean = (ModeratorBean) session.getAttribute("modBean");
        if (modBean == null) {
            modBean = new ModeratorBean();
        }
        modBean.setGuestbookName(nameGB);
        modBean.setUsername(login.getUserName());
        login.setModerator(modBean.isModerator());

        return page + ".jsp";
    }
}
