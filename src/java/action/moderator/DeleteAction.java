package action.moderator;

import action.AbstractGuestbookAction;
import beans.Login;
import beans.MessageBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * DeleteAction - ��������. ��������� ���� ����� perfom(). ��������
 * ��������� ��������� ������ � �������� ���������� ���������, � �����
 * ������������� �������� delete.jsp.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class DeleteAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ��������� ������ � �������� ���������� ���������, � �����
     * ������������� �������� delete.jsp.
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

        /*
         * ���� ������������ �� ������������� ��� �� ���������
         * ������������ ��� �� �������� ����������� login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isModerator()) {
                MessageBean mBean = (MessageBean) session.getAttribute("mBean");
                if (mBean == null) {
                    mBean = new MessageBean();
                }

                mBean.setDataSource(datasource);
                mBean.setId(Integer.parseInt(request.getParameter("msgID")));
                mBean.getMessageById();
                mBean.deleteMessage();
                session.setAttribute("mBean", mBean);
                session.removeAttribute("gBean");

                return "/moderator/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
