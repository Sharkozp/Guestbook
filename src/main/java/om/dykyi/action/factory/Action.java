package om.dykyi.action.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Action - Интерфейс Action.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public interface Action {

    String perform(HttpServletRequest request, HttpServletResponse response);
}
