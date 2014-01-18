package action.moderator;

import action.AbstractGuestbookAction;
import beans.GuestbookBean;
import beans.Login;
import beans.MessageBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddCorrectionAction - ��������. ��������� ���� ����� perfom(). ��������
 * ��������� ��������� � ��������� ������� �� ��������� ������ ������������
 * �����������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AddCorrectionAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ��������� � ��������� ������� �� ��������� ������
     * ������������ �����������.
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
                mBean.setMessage(request.getParameter("messageText"));
                if (Boolean.parseBoolean(request.getParameter("isForAll"))) {
                    mBean.setForAll(true);
                } else {
                    mBean.setForAll(false);
                }
                mBean.updateMessage();

                //��������� ������ �����
                GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
                if (gBean == null) {
                    gBean = new GuestbookBean();
                }
                gBean.setDataSource(datasource);
                String nameGB;
                if (request.getParameter("nameGuestbook") == null || request.getParameter("nameGuestbook").length() == 0) {
                    gBean.getListOfGuestbook();
                    nameGB = gBean.getGuestbooks().get(0).getName();
                } else {
                    nameGB = request.getParameter("nameGuestbook");
                }

                session.setAttribute("gBean", gBean);

                //��������� ������ ���������
                mBean.setDataSource(datasource);
                mBean.setGuestbookName(nameGB);
                mBean.getListOfMessages();
                mBean.getMessageCount();
                session.setAttribute("mBean", mBean);
                return "/guestbook.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
