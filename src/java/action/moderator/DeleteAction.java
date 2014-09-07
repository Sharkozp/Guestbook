package action.moderator;

import action.AbstractGuestbookAction;
import beans.Login;
import beans.MessageBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * DeleteAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет обработку данных и удаление выбранного сообщения, а также
 * инициализацию страницы delete.jsp.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class DeleteAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет обработку данных и удаление выбранного сообщения, а также
     * инициализацию страницы delete.jsp.
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

        /*
         * Если пользователь не авторизирован или не модератор
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isModerator()) {
                MessageBean mBean = (MessageBean) session.getAttribute("mBean");
                if (mBean == null) {
                    mBean = new MessageBean();
                }

                mBean.setDataSource(datasource);
                mBean.setId(Integer.parseInt(request.getParameter("msgID")));
                mBean.getMessageById();
                mBean.deleteMessage();
                session.setAttribute("mBean", mBean);
                session.removeAttribute("gBean");

                return "/moderator/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
