package om.dykyi.action;

import om.dykyi.beans.GuestbookBean;
import om.dykyi.beans.Login;
import om.dykyi.beans.ModeratorBean;
import om.dykyi.beans.UserBean;
import om.dykyi.dao.user.UserModel;
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
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class CheckLoginAction extends AbstractGuestbookAction {

    /**
     * Метод выполняет проверку введенных данных и от результата переадресует на
     * определенную страницу.
     *
     * @param request    Запрос к сервлету
     * @param response   Ответ сервлета
     * @param datasource Источник данных для пула данных
     * @return URL-адрес
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {

        String page;
        HttpSession session = request.getSession();

        // Возврат со страницы login.jsp при нажатии конпки продолжить
        UserBean uBean = (UserBean) session.getAttribute("uBean");
        if (uBean == null) {
            uBean = new UserBean();
        }
        String userName = request.getParameter("login");
        String password = request.getParameter("password");

        //если заполнены проверяем введеные данные
        /* uBean.setUserName(userName);
        try {
            uBean.setPwdDigest(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage());
        }*/

        /**
         * Если пользователь существует с парой логин, пароль выполняем вход и
         * записываем в сессию объект Login
         */
        if (isUserExist(userName, password)) {
            ModeratorBean modBean = new ModeratorBean();
            page = "message";
            Login login = new Login();
            login.setUserName(userName);
            modBean.setUsername(userName);
            login.setModerator(modBean.isModerator());
            login.setAdmin(uBean.isAdmin(userName));
            session.setAttribute("login", login);
            session.removeAttribute("uBean");

            GuestbookBean gBean = (GuestbookBean) session.getAttribute("gBean");
            if (gBean == null) {
                gBean = new GuestbookBean();
            }
            gBean.getGuestbookList();
            session.setAttribute("gBean", gBean);
        } else {
            //Иначе выбрасываем ошибку на страницу login.jsp
            page = "login";
            uBean.setError(true);
            session.setAttribute("uBean", uBean);
        }


        return page + ".jsp";
    }

    /**
     * Метод проверяет существует ли пользователь в базе данных
     *
     * @return признак
     */
    private boolean isUserExist(String userName, String password) {
        PropertiesClass propertiesClass = PropertiesClass.getInstance();
        String securePassword = new UserModel().getUserDigest(userName);

        return PasswordUtils.verifyUserPassword(password, securePassword, propertiesClass.getSystemKey());
    }
}
