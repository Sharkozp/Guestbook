package action;

import beans.UserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * LoginAction - ��������. ��������� ���� ����� perfom(). �������� ��������� 
 * ������������� �������� login.jsp.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class LoginAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ������������� �������� login.jsp.
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
        UserBean uBean = (UserBean) session.getAttribute("uBean");
        if (uBean == null) {
            uBean = new UserBean();
        }
        uBean.setDataSource(datasource);
        uBean.setError(false);
        session.setAttribute("uBean", uBean);
        session.removeAttribute("gBean");

        return page + ".jsp";
    }
}
