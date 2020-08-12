package om.dykyi.action;

import om.dykyi.beans.MessageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

/**
 * AddMessageAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * получение и проверку сообщения, обрезку до 4 байт.
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class AddMessageAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет получение и проверку сообщения, обрезку до 4 байт.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("command").toLowerCase();
        HttpSession session = request.getSession();
        MessageBean mBean = (MessageBean) session.getAttribute("mBean");
        if (mBean == null) {
            mBean = new MessageBean();
        }



        mBean.setMessage(request.getParameter("message"));
        mBean.setAuthorName(request.getParameter("authorName"));
        // Guestbook ID
        mBean.setGuestbookName(request.getParameter("guestbook"));
        mBean.setPhone(request.getParameter("phone"));
        mBean.setEmail(request.getParameter("email"));
        mBean.setIcq(request.getParameter("icq"));
        mBean.setTimeCreation(new Date());
        mBean.setAuthorIP(request.getRemoteHost());
        boolean isForAll = Boolean.parseBoolean(request.getParameter("forAll"));
        mBean.setForAll(isForAll);

        mBean.addMessage();
        session.setAttribute("mBean", mBean);
        session.removeAttribute("gBean");

        return page + ".jsp";
    }
}
