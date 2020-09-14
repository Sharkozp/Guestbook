package om.dykyi.action.moderator;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Guestbook;
import om.dykyi.beans.Login;
import om.dykyi.beans.Message;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.message.MessageDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * AddCorrectionAction - подкласс. Реализует один метод perfom(). Подкласс
 * выполняет получение и обработку запроса на изменение текста пользователя
 * модератором.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class AddEditAction extends AbstractModeratorAction {

    /**
     * Метод выполняет получение и обработку запроса на изменение текста
     * пользователя модератором.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        // check authorisation
        String result = super.perform(request, response);
        if (result == null) {
            boolean isForAll = Boolean.parseBoolean(request.getParameter("forAll"));
            String messageText = request.getParameter("message");
            int messageId = Integer.parseInt(request.getParameter("messageId"));

            MysqlDAOFactory daoFactory = (MysqlDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            MessageDAO messageDAO = daoFactory.getMessageDao();
            Message message = messageDAO.getMessage(messageId);
            message.setForAll(isForAll);
            message.setMessage(messageText);
            messageDAO.updateMessage(message);

            result = "/guestbook.jsp";
        }

        return result;
    }
}
