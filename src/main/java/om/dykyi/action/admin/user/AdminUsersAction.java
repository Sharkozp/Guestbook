package om.dykyi.action.admin.user;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Login;
import om.dykyi.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AdminUsersAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы adminusers.jsp.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class AdminUsersAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы adminusers.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        session.removeAttribute("gBean");
        session.removeAttribute("mBean");

        /*
         * Если пользователь не авторизирован или не админ
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                User uBean = (User) session.getAttribute("uBean");
                if (uBean == null) {
                    uBean = new User();
                }
        //        uBean.getListOfUsers();
                session.setAttribute("uBean", uBean);

                return "/admin/user/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
