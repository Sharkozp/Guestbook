package action.admin.user;

import action.AbstractGuestbookAction;
import beans.Login;
import beans.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AdminUsersAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * ������������� �������� adminusers.jsp.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AdminUsersAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ������������� �������� adminusers.jsp.
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
        session.removeAttribute("gBean");
        session.removeAttribute("mBean");

        /*
         * ���� ������������ �� ������������� ��� �� �����
         * ������������ ��� �� �������� ����������� login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                UserBean uBean = (UserBean) session.getAttribute("uBean");
                if (uBean == null) {
                    uBean = new UserBean();
                }
                uBean.setDataSource(datasource);
                uBean.getListOfUsers();
                session.setAttribute("uBean", uBean);

                return "/admin/user/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
