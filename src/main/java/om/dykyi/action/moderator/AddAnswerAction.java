package om.dykyi.action.moderator;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Guestbook;
import om.dykyi.beans.Login;
import om.dykyi.beans.Message;
import om.dykyi.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AddAnswerAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * получение и обработку запроса на добавление ответа сообщения модератором.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class AddAnswerAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и обработку запроса на добавление ответа
     * сообщения модератором.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        /*
         * Если пользователь не авторизирован или не модератор
         * переадресуем его на страницу авторизации login.jsp
         */
        Login login = (Login) session.getAttribute("login");
        if (login != null) {
            if (login.isModerator()) {
                User uBean = (User) session.getAttribute("uBean");
                if (uBean == null) {
                    uBean = new User();
                }
             //   uBean.setUserName(login.getUserName());
        //        uBean.getUser();

                Message mBean = (Message) session.getAttribute("mBean");
                if (mBean == null) {
          //          mBean = new Message();
                }

            //    mBean.setAnswerName(uBean.getLastName() + " " + uBean.getFirstName());
            //    mBean.setAnswerText(request.getParameter("answerText"));
            //    mBean.setAnswerTime(new Date());
       //         mBean.setAnswer();

                //обновляем сессию книги
                Guestbook gBean = (Guestbook) session.getAttribute("gBean");
                if (gBean == null) {
       //             gBean = new Guestbook();
                }
                String nameGB;
                if (request.getParameter("nameGuestbook") == null || request.getParameter("nameGuestbook").length() == 0) {
        //            gBean.getGuestbookList();
        //            nameGB = gBean.getGuestbooks().get(0).getName();
                } else {
                    nameGB = request.getParameter("nameGuestbook");
                }

                session.setAttribute("gBean", gBean);

                //обновляем сессию сообщение
       //         mBean.setGuestbookId(nameGB);
         //       mBean.getListOfMessages();
         //       mBean.getMessageCount();
                session.setAttribute("mBean", mBean);

                return "/guestbook.jsp";
            } else {
                return "login.jsp";
            }
        } else {
            return "login.jsp";
        }
    }
}
