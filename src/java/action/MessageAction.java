package action;

import beans.GuestbookBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * MessageAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * ������������� �������� message.jsp.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class MessageAction extends AbstractGuestbookAction {

    public static final Logger log = Logger.getLogger(MessageAction.class);

    /**
     * ����� ��������� ������������� �������� message.jsp.
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

        GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
        if (gBean == null) {
            gBean = new GuestbookBean();
        }
        gBean.setDataSource(datasource);
        gBean.getListOfGuestbook();
        session.setAttribute("gBean", gBean);
        session.removeAttribute("mBean");


        return page + ".jsp";
    }
}
