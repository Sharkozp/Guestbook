package action.admin.book;

import action.AbstractGuestbookAction;
import beans.GuestbookBean;
import beans.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddChangeBookAction - ��������. ��������� ���� ����� perfom(). ��������
 * ��������� ��������� ������� � ������ ����� ������ � �������� �����.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AddChangeBookAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ��������� ������� � ������ ����� ������ � ��������
     * �����.
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
                GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
                if (gBean == null) {
                    gBean = new GuestbookBean();
                }
                gBean.setDataSource(datasource);
                gBean.setNameGuestbook(request.getParameter("newNamebook"));
                gBean.setDescription(request.getParameter("newDescription"));
                gBean.setDisplayOrder(Integer.parseInt(request.getParameter("newDisplayOrder")));
                gBean.updateBook();

                gBean.setDataSource(datasource);
                gBean.getListOfGuestbook();
                session.setAttribute("gBean", gBean);

                return "/admin/book/adminbooks.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
