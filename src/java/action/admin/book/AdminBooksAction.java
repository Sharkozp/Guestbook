package action.admin.book;

import action.AbstractGuestbookAction;
import beans.GuestbookBean;
import beans.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * AdminBooksAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет инициализацию страницы adminbooks.jsp.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class AdminBooksAction extends AbstractGuestbookAction {

    public static final Logger log = Logger.getLogger(AdminBooksAction.class);

    /**
     * Метод выполняет инициализацию страницы adminbooks.jsp.
     *
     * @param request Запрос к сервлету
     * @param response Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response, DataSource datasource) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        session.removeAttribute("mBean");

        /*
         * Если пользователь не авторизирован или не админ
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
                if (gBean == null) {
                    gBean = new GuestbookBean();
                }
                gBean.setDataSource(datasource);
                gBean.getListOfGuestbook();
                session.setAttribute("gBean", gBean);

                return "/admin/book/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
