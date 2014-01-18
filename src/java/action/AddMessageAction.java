package action;

import beans.MessageBean;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * AddMessageAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * ��������� � �������� ���������, ������� �� 4 ����.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class AddMessageAction extends AbstractGuestbookAction {

    public static final Logger log = Logger.getLogger(AddMessageAction.class);

    /**
     * ����� ��������� ��������� � �������� ���������, ������� �� 4 ����.
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
        MessageBean mBean = (MessageBean) session.getAttribute("mBean");
        if (mBean == null) {
            mBean = new MessageBean();
            mBean.setDataSource(datasource);
        }

        String msg = request.getParameter("message");

        byte[] b = null;

        try {
            b = msg.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        if (b.length > 4096) {
            mBean.setMessage(new String(Arrays.copyOf(msg.getBytes(), 4096)));
        } else {
            mBean.setMessage(msg);
        }
        mBean.setAuthorName(request.getParameter("authorName"));
        mBean.setGuestbookName(request.getParameter("guestbookName"));
        mBean.setPhone(request.getParameter("phone"));
        mBean.setEmail(request.getParameter("email"));
        mBean.setIcq(request.getParameter("icq"));
        mBean.setTimeCreation(new Date());
        mBean.setAuthorIP(request.getRemoteHost());
        if (Boolean.parseBoolean(request.getParameter("forAll"))) {
            mBean.setForAll(true);
        } else {
            mBean.setForAll(false);
        }
        mBean.addMessage();
        session.setAttribute("mBean", mBean);
        session.removeAttribute("gBean");

        return page + ".jsp";
    }
}
