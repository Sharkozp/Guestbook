package action;

import beans.GuestbookBean;
import beans.Login;
import beans.ModeratorBean;
import beans.UserBean;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * CheckLoginAction - ��������. ��������� ���� ����� perfom(). �������� ���������
 * �������� ��������� ������ � �� ���������� ������������ �� ������������
 * ��������.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class CheckLoginAction extends AbstractGuestbookAction {

    public static final Logger log = Logger.getLogger(CheckLoginAction.class);

    /**
     * ����� ��������� �������� ��������� ������ � �� ���������� ������������ ��
     * ������������ ��������.
     *
     * @param request ������ � ��������
     * @param response ����� ��������
     * @param datasource �������� ������ ��� ���� ������
     * @return URL-�����
     */
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response, DataSource datasource) {

        String page;
        HttpSession session = request.getSession();

        /**
         * ������� �� �������� login.jsp ��� ������� ������ ����������
         */
        UserBean uBean = (UserBean) session.getAttribute("uBean");
        if (uBean == null) {
            uBean = new UserBean();
        }
        uBean.setDataSource(datasource);
        String userName = request.getParameter("login");
        String password = request.getParameter("password");

        //���� ��������� ��������� �������� ������
        uBean.setUserName(userName);
        try {
            uBean.setPwdDigest(password);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }

        /**
         * ���� ������������ ���������� � ����� �����, ������ ��������� ���� �
         * ���������� � ������ ������ Login
         */
        if (uBean.isUserExist()) {
            ModeratorBean modBean = new ModeratorBean();
            page = "message";
            modBean.setDataSource(datasource);
            Login login = new Login();
            login.setUserName(userName);
            modBean.setUsername(userName);
            login.setModerator(modBean.isModerator());
            login.setAdmin(uBean.isAdmin());
            session.setAttribute("login", login);
            session.removeAttribute("uBean");

            GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
            if (gBean == null) {
                gBean = new GuestbookBean();
            }
            gBean.setDataSource(datasource);
            gBean.getListOfGuestbook();
            session.setAttribute("gBean", gBean);
        } else {
            //����� ����������� ������ �� �������� login.jsp
            page = "login";
            uBean.setError(true);
            session.setAttribute("uBean", uBean);
        }


        return page + ".jsp";
    }
}
