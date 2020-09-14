package om.dykyi.action.moderator;

import om.dykyi.beans.Message;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.message.MessageDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * CorrectionAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет инициализацию страницы correction.jsp.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class EditAction extends AbstractModeratorAction {

    /**
     * Метод выполняет инициализацию страницы correction.jsp.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        // check authorisation
        String result = super.perform(request, response);
        if (result == null) {
            HttpSession session = request.getSession();
            int messageId = Integer.parseInt(request.getParameter("messageId"));
            MysqlDAOFactory daoFactory = (MysqlDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            MessageDAO messageDAO = daoFactory.getMessageDao();
            Message message = messageDAO.getMessage(messageId);
            session.setAttribute("message", message);
            result = getPage(request);
        }

        return result;
    }
}
