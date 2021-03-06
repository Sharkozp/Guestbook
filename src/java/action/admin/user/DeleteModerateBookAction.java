package action.admin.user;

import action.AbstractGuestbookAction;
import beans.Login;
import beans.ModeratorBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * DeleteModerateBookAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет получение и обработку запроса на удаление книги у модератора.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class DeleteModerateBookAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и обработку запроса на удаление книги у
     * модератора.
     *
     * @param request Запрос к сервлету
     * @param response Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response, DataSource datasource) {

        HttpSession session = request.getSession();

        /*
         * Если пользователь не авторизирован или не админ
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isAdmin()) {
                ModeratorBean modBean = (ModeratorBean) session.getAttribute("modBean");
                if (modBean == null) {
                    modBean = new ModeratorBean();
                }
                modBean.setDataSource(datasource);
                modBean.setId(Integer.parseInt(request.getParameter("idMod")));
                modBean.deleteModeratorBook();

                modBean.setDataSource(datasource);
                modBean.setUsername(request.getParameter("userName"));
                modBean.getListBooks();
                session.setAttribute("modBean", modBean);

                return "/admin/user/changeuser.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
