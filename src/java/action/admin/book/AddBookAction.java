package action.admin.book;

import action.AbstractGuestbookAction;
import beans.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddBookAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы addbook.jsp.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class AddBookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы addbook.jsp.
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
        session.removeAttribute("gBean");

        /*
         * Если пользователь не авторизирован или не админ
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                return "/admin/book/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
