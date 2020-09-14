package om.dykyi.action.admin.user;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Login;
import om.dykyi.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * DeleteBookAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет получение и обработку запроса на удаление пользователя.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class DeleteUserAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и обработку запроса на удаление пользователя.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

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
            //    uBean.setUserName(request.getParameter("userName"));
            //    uBean.deleteUser();

            //    uBean.getListOfUsers();
                session.setAttribute("uBean", uBean);

                return "/admin/user/adminusers.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
