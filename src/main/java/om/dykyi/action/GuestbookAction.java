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
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {
        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        session.removeAttribute("uBean");
        GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
        if (gBean == null) {
            gBean = new GuestbookBean();
        }
        gBean.setDataSource(datasource);
        String nameGB;
        if (request.getParameter("nameGuestbook") == null || request.getParameter("nameGuestbook").length() == 0) {
            gBean.getListOfGuestbook();
            nameGB = gBean.getGuestbooks().get(0).getName();
        } else {
            nameGB = request.getParameter("nameGuestbook");
        }

        session.setAttribute("gBean", gBean);
        MessageBean mBean = (MessageBean) session.getAttribute("mBean");
        if (mBean == null) {
            mBean = new MessageBean();
        }
        mBean.setDataSource(datasource);
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
        modBean.setDataSource(datasource);
        modBean.setGuestbookName(nameGB);
        modBean.setUsername(login.getUserName());
        login.setModerator(modBean.isModerator());

        return page + ".jsp";
    }
}
