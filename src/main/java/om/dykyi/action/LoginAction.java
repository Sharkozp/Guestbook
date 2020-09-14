package om.dykyi.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы login.jsp.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class LoginAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы login.jsp.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("error", false);

        return getPage(request);
    }
}
