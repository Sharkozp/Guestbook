package action.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Action - Интерфейс Action.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public interface Action {

    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource);
}
