package om.dykyi.action.admin.user;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Login;
import om.dykyi.beans.ModeratorBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddModerateBookAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет получение и обработку запроса на добавление книги модератору.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class AddModerateBookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и обработку запроса на добавление книги
     * модератору.
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
                ModeratorBean modBean = (ModeratorBean) session.getAttribute("modBean");
                if (modBean == null) {
                    modBean = new ModeratorBean();
                }
                modBean.setGuestbookName(request.getParameter("newModerBook"));
                modBean.setUsername(request.getParameter("userName"));
                modBean.setModeratorsBook();

                modBean.setUsername(request.getParameter("userName"));
                modBean.getListBooks();
                session.setAttribute("modBean", modBean);

                return "/admin/user/changeuser.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
