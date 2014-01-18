package action;

import beans.GuestbookBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * MessageAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * инициализацию страницы message.jsp.
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class MessageAction extends AbstractGuestbookAction {

    public static final Logger log = Logger.getLogger(MessageAction.class);

    /**
     * Метод выполняет инициализацию страницы message.jsp.
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

        GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
        if (gBean == null) {
            gBean = new GuestbookBean();
        }
        gBean.setDataSource(datasource);
        gBean.getListOfGuestbook();
        session.setAttribute("gBean", gBean);
        session.removeAttribute("mBean");


        return page + ".jsp";
    }
}
