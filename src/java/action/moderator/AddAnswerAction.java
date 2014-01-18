package action.moderator;

import action.AbstractGuestbookAction;
import beans.GuestbookBean;
import beans.Login;
import beans.MessageBean;
import beans.UserBean;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddAnswerAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * ��������� � ��������� ������� �� ���������� ������ ��������� �����������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AddAnswerAction extends AbstractGuestbookAction {

    /**
     * ����� ��������� ��������� � ��������� ������� �� ���������� ������
     * ��������� �����������.
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
                UserBean uBean = (UserBean) session.getAttribute("uBean");
                if (uBean == null) {
                    uBean = new UserBean();
                }
                uBean.setDataSource(datasource);
                uBean.setUserName(login.getUserName());
                uBean.getUser();

                MessageBean mBean = (MessageBean) session.getAttribute("mBean");
                if (mBean == null) {
                    mBean = new MessageBean();
                }

                mBean.setDataSource(datasource);

                mBean.setAnswerName(uBean.getLastName() + " " + uBean.getFirstName());
                mBean.setAnswerText(request.getParameter("answerText"));
                mBean.setAnswerTime(new Date());
                mBean.setAnswer();

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
