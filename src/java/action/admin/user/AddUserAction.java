package action.admin.user;

import action.AbstractGuestbookAction;
import beans.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddUserAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * ������������� �������� adduser.jsp.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AddUserAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ������������� �������� adduser.jsp.
     *
     * @param request ������ � ��������
     * @param response ����� ��������
     * @param datasource �������� ������ ��� ���� ������
     * @return URL-�����
     */
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response, DataSource datasource) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        session.removeAttribute("uBean");

        /*
         * ���� ������������ �� ������������� ��� �� �����
         * ������������ ��� �� �������� ����������� login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                return "/admin/user/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
