package action;

import action.factory.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractGuestbookAction - абстрактный класс. Реализует один метод perfom().
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public abstract class AbstractGuestbookAction implements Action {

    public static final Logger log = Logger.getLogger(AddMessageAction.class);

    /**
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
