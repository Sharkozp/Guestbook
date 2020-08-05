package om.dykyi.action.admin.user;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.GuestbookBean;
import om.dykyi.beans.Login;
import om.dykyi.beans.ModeratorBean;
import om.dykyi.beans.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * ChangeUserAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет инициализацию страницы changeuser.jsp.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class ChangeUserAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы changeuser.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();

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
                String user = request.getParameter("userName");
                uBean.setUserName(user);
                uBean.getUser();
                session.setAttribute("uBean", uBean);

                ModeratorBean modBean = (ModeratorBean) session.getAttribute("modBean");
                if (modBean == null) {
                    modBean = new ModeratorBean();
                }
                modBean.setDataSource(datasource);
                modBean.setUsername(user);
                modBean.getListBooks();
                session.setAttribute("modBean", modBean);

                GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
                if (gBean == null) {
                    gBean = new GuestbookBean();
                }
                gBean.setDataSource(datasource);
                gBean.getGuestbookList();
                session.setAttribute("gBean", gBean);

                return "/admin/user/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
