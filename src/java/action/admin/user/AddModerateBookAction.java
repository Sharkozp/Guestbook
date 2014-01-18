package action.admin.user;

import action.AbstractGuestbookAction;
import beans.Login;
import beans.ModeratorBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddModerateBookAction - ��������. ��������� ���� ����� perfom(). ��������
 * ��������� ��������� � ��������� ������� �� ���������� ����� ����������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AddModerateBookAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ��������� � ��������� ������� �� ���������� �����
     * ����������.
     *
     * @param request ������ � ��������
     * @param response ����� ��������
     * @param datasource �������� ������ ��� ���� ������
     * @return URL-�����
     */
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response, DataSource datasource) {

        HttpSession session = request.getSession();

        /*
         * ���� ������������ �� ������������� ��� �� �����
         * ������������ ��� �� �������� ����������� login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                ModeratorBean modBean = (ModeratorBean) session.getAttribute("modBean");
                if (modBean == null) {
                    modBean = new ModeratorBean();
                }
                modBean.setDataSource(datasource);
                modBean.setGuestbookName(request.getParameter("newModerBook"));
                modBean.setUsername(request.getParameter("userName"));
                modBean.setModeratorsBook();

                modBean.setDataSource(datasource);
                modBean.setUsername(request.getParameter("userName"));
                modBean.getListBooks();
                session.setAttribute("modBean", modBean);

                return "/admin/user/changeuser.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
