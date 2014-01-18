package action;

import action.factory.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractGuestbookAction - абстрактный класс. Реализует один метод perfom().
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public abstract class AbstractGuestbookAction implements Action {

    /**
     * @param request Запрос к сервлету
     * @param response Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
