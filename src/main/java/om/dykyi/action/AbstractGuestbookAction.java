package om.dykyi.action;

import om.dykyi.action.factory.Action;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractGuestbookAction - абстрактный класс. Реализует один метод perfom().
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public abstract class AbstractGuestbookAction implements Action {

    public static final Logger LOGGER = Logger.getLogger(AbstractGuestbookAction.class);

    /**
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return jsp page
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public String getPage(HttpServletRequest request) {
        String page = request.getParameter("command").toLowerCase();
        return page + ".jsp";
    }
}
