package om.dykyi.action;

import om.dykyi.beans.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * LoginAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы login.jsp.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class LoginAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы login.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        UserBean uBean = (UserBean) session.getAttribute("uBean");
        if (uBean == null) {
            uBean = new UserBean();
        }
        uBean.setError(false);
        session.setAttribute("uBean", uBean);
        session.removeAttribute("gBean");

        return page + ".jsp";
    }
}
