package om.dykyi.action;

import com.sun.org.apache.xpath.internal.operations.Mod;
import om.dykyi.beans.Guestbook;
import om.dykyi.beans.Login;
import om.dykyi.beans.ModeratorBean;
import om.dykyi.beans.User;
import om.dykyi.dao.factory.DAOFactory;
import om.dykyi.dao.factory.MysqlDAOFactory;
import om.dykyi.dao.guestbook.GuestbookDAO;
import om.dykyi.dao.moderator.ModeratorDAO;
import om.dykyi.dao.user.MysqlUserDAO;
import om.dykyi.dao.user.UserDAO;
import om.dykyi.system.PasswordUtils;
import om.dykyi.system.PropertiesClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * CheckLoginAction - подкласс. Реализует один метод perfom(). Подкласс выполняет
 * проверку введенных данных и от результата переадресует на определенную
 * страницу.
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class CheckLoginAction extends AbstractGuestbookAction {

    private UserDAO userDAO;
    private ModeratorDAO moderatorDAO;

    /**
     * Метод выполняет проверку введенных данных и от результата переадресует на
     * определенную страницу.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        MysqlDAOFactory daoFactory = (MysqlDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        userDAO = daoFactory.getUserDao();
        HttpSession session = request.getSession();

        String username = request.getParameter("login");
        String password = request.getParameter("password");

        String page = "login";
        boolean error = true;
        //check if user exist in DB
        if (isUserExist(username, password)) {
            User user = userDAO.getUser(username);
            session.setAttribute("user", user);
            error = false;
            page = "message";
        }

        session.setAttribute("error", error);
        return page + ".jsp";
    }

    /**
     * Метод проверяет существует ли пользователь в базе данных
     *
     * @return признак
     */
    private boolean isUserExist(String username, String password) {
        String pwdDigest = userDAO.getUserDigest(username);
        if (pwdDigest == null) {
            return false;
        }
        PropertiesClass propertiesClass = PropertiesClass.getInstance();
        return PasswordUtils.verifyUserPassword(password, pwdDigest, propertiesClass.getSystemKey());
    }

    /**
     * Метод принимает строку для создания дайджеста пароля
     *
     * @param password пароль
     * @return password digest
     */
    public String getPwdDigest(String password) {
        PropertiesClass propertiesClass = PropertiesClass.getInstance();

        return PasswordUtils.generateSecurePassword(password, propertiesClass.getSystemKey());
    }
}
