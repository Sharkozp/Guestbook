package action.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Action - ��������� Action.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public interface Action {

    public String perform(HttpServletRequest request, 
       HttpServletResponse response, DataSource datasource);
}
