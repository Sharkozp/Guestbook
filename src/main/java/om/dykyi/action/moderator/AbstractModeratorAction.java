package om.dykyi.action.moderator;

import om.dykyi.action.AbstractGuestbookAction;
import om.dykyi.beans.Message;
import om.dykyi.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

abstract public class AbstractModeratorAction extends AbstractGuestbookAction {

    /**
     * In this method for moderator checks if user is moderator and authorised, if not - redirect to login.jsp
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        // Check if user is moderator and authorised, if not - redirect to login.jsp
        User user = (User) session.getAttribute("user");
        boolean isModerator = (boolean) session.getAttribute("moderator");
        if (user == null || !isModerator) {
            return "login.jsp";
        }

        return null;
    }

    @Override
    public String getPage(HttpServletRequest request) {
        return "/moderator/" + super.getPage(request);
    }
}
