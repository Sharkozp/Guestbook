package om.dykyi.action.admin.book;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Guestbook;
import om.dykyi.beans.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AddNewBookAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет обработку запроса и запись новой гостевой книги.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class AddNewBookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет обработку запроса и запись новой гостевой книги.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        /*
         * Если пользователь не авторизирован или не админ
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                Guestbook gBean = (Guestbook) session.getAttribute("gBean");
                if (gBean == null) {
        //            gBean = new Guestbook();
                }
         //       gBean.setName(request.getParameter("newNamebook"));
        //        gBean.setDescription(request.getParameter("newDescription"));
        //        gBean.setDisplayOrder(Integer.parseInt(request.getParameter("newDisplayOrder")));
        //        gBean.addBook();

         //       gBean.getGuestbookList();
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
