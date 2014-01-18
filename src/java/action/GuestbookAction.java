package action;

import beans.GuestbookBean;
import beans.Login;
import beans.MessageBean;
import beans.ModeratorBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * GuestbookAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * ������������� � �������� ���� ��������� � ������� �������� ����� ��� ��
 * ��������� �� �������� guestbook.jsp.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class GuestbookAction extends AbstractGuestbookAction {

    public static final Logger log = Logger.getLogger(GuestbookAction.class);

    /**
     * ����� ��������� ������������� � �������� ���� ��������� � �������
     * �������� ����� ��� �� ��������� �� �������� guestbook.jsp.
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
        MessageBean mBean = (MessageBean) session.getAttribute("mBean");
        if (mBean == null) {
            mBean = new MessageBean();
        }
        mBean.setDataSource(datasource);
        mBean.setGuestbookName(nameGB);
        mBean.getListOfMessages();
        mBean.getMessageCount();
        session.setAttribute("mBean", mBean);

        Login login = (Login) session.getAttribute("login");
        if (login == null) {
            login = new Login();

        }
        ModeratorBean modBean = (ModeratorBean) session.getAttribute("modBean");
        if (modBean == null) {
            modBean = new ModeratorBean();
        }
        modBean.setDataSource(datasource);
        modBean.setGuestbookName(nameGB);
        modBean.setUsername(login.getUserName());
        login.setModerator(modBean.isModerator());

        return page + ".jsp";
    }
}
