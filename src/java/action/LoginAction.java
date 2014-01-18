package action;

import beans.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * LoginAction - подкласс. Реализует один метод perfom(). Подкласс выполняет 
 * инициализацию страницы login.jsp.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class LoginAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы login.jsp.
     *
     * @param request Запрос к сервлету
     * @param response Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response, DataSource datasource) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        UserBean uBean = (UserBean) session.getAttribute("uBean");
        if (uBean == null) {
            uBean = new UserBean();
        }
        uBean.setDataSource(datasource);
        uBean.setError(false);
        session.setAttribute("uBean", uBean);
        session.removeAttribute("gBean");

        return page + ".jsp";
    }
}
