package action.moderator;

import action.AbstractGuestbookAction;
import beans.GuestbookBean;
import beans.Login;
import beans.MessageBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * AddCorrectionAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет получение и обработку запроса на изменение текста пользователя
 * модератором.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class AddCorrectionAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и обработку запроса на изменение текста
     * пользователя модератором.
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
                mBean.setMessage(request.getParameter("messageText"));
                if (Boolean.parseBoolean(request.getParameter("isForAll"))) {
                    mBean.setForAll(true);
                } else {
                    mBean.setForAll(false);
                }
                mBean.updateMessage();

                //обновляем сессию книги
                GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
                if (gBean == null) {
                    gBean = new GuestbookBean();
                }
                gBean.setDataSource(datasource);
                String nameGB;
                if (request.getParameter("nameGuestbook") == null || request.getParameter("nameGuestbook").length() == 0) {
                    gBean.getListOfGuestbook();
                    nameGB = gBean.getGuestbooks().get(0).getName();
                } else {
                    nameGB = request.getParameter("nameGuestbook");
                }

                session.setAttribute("gBean", gBean);

                //обновляем сессию сообщение
                mBean.setDataSource(datasource);
                mBean.setGuestbookName(nameGB);
                mBean.getListOfMessages();
                mBean.getMessageCount();
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
