package action.admin.book;

import action.AbstractGuestbookAction;
import beans.GuestbookBean;
import beans.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddNewBookAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет обработку запроса и запись новой гостевой книги.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class AddNewBookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет обработку запроса и запись новой гостевой книги.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    @Override
    public String perform(HttpServletRequest request, HttpServletResponse response, DataSource datasource) {

        HttpSession session = request.getSession();

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
                gBean.setNameGuestbook(request.getParameter("newNamebook"));
                gBean.setDescription(request.getParameter("newDescription"));
                gBean.setDisplayOrder(Integer.parseInt(request.getParameter("newDisplayOrder")));
                gBean.addBook();

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
