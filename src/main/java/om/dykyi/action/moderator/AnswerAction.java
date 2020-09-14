package om.dykyi.action.moderator;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Login;
import om.dykyi.beans.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AnswerAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы answer.jsp.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class AnswerAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет инициализацию страницы answer.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();

        /*
         * Если пользователь не авторизирован или не модератор
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isModerator()) {
                Message mBean = (Message) session.getAttribute("mBean");
                if (mBean == null) {
         //           mBean = new Message();
                }

        //        mBean.setId(Integer.parseInt(request.getParameter("msgID")));
       //         mBean.getMessageById();
                session.setAttribute("mBean", mBean);

                return "/moderator/" + page + ".jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
