package om.dykyi.action.admin.book;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.GuestbookBean;
import om.dykyi.beans.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AdminBooksAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет инициализацию страницы adminbooks.jsp.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class AdminBooksAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы adminbooks.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        session.removeAttribute("mBean");

        /*
         * Если пользователь не авторизирован или не админ
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
                if (gBean == null) {
                    gBean = new GuestbookBean();
                }
                gBean.setDataSource(datasource);
                gBean.getGuestbookList();
                session.setAttribute("gBean", gBean);

                return "/admin/book/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
