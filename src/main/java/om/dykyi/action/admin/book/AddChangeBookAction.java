package om.dykyi.action.admin.book;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.GuestbookBean;
import om.dykyi.beans.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddChangeBookAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет обработку запроса и запись новых данных о гостевой книге.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class AddChangeBookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет обработку запроса и запись новых данных о гостевой
     * книге.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {

        HttpSession session = request.getSession();

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
                gBean.setGuestbookName(request.getParameter("newNamebook"));
                gBean.setDescription(request.getParameter("newDescription"));
                gBean.setDisplayOrder(Integer.parseInt(request.getParameter("newDisplayOrder")));
                gBean.updateBook();

                gBean.getGuestbookList();
                session.setAttribute("gBean", gBean);

                return "/admin/book/adminbooks.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
