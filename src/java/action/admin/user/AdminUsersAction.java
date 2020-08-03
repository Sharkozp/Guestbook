package action.admin.user;

import action.AbstractGuestbookAction;
import beans.Login;
import beans.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AdminUsersAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы adminusers.jsp.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class AdminUsersAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы adminusers.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {

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
                UserBean uBean = (UserBean) session.getAttribute("uBean");
                if (uBean == null) {
                    uBean = new UserBean();
                }
                uBean.setDataSource(datasource);
                uBean.getListOfUsers();
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
