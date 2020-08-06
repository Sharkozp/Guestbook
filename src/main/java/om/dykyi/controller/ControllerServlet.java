package om.dykyi.controller;

import om.dykyi.action.factory.Action;
import om.dykyi.action.factory.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ControllerServlet - обьект контроллер в моделе MVC. Класс ControllerServlet -
 * обрабатывает все запросы пользователей и переадресует на соответствующие JSP
 * страницы, посредством нажатия кнопок на JSP страницах.
 *
 * @author Дикий Александр Николаевич
 * @version 1.1
 */
public class ControllerServlet extends HttpServlet {

    /**
     * Логирование класса ControllerServlet.class
     */
    public static final Logger log = Logger.getLogger(ControllerServlet.class);
    protected ActionFactory factory = new ActionFactory();
    private ConnectionPool conPool;

    public ControllerServlet() {
        conPool = ConnectionPool.getInstance();
    }

    /**
     * Инициализация сервлета-контроллера. Выполняется создания пула соединений.
     *
     */
    @Override
    public void init() {
        conPool.initDataSource();
    }

    /**
     * Обработка запросов для обоих методов HTTP (от GET и от POST).
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command != null || command.length() != 0) {
            Action action = factory.create(command);
            String url = action.perform(request, response);
            if (url != null) {
                try {
                    String newUrl = "/jsp/" + url;
                    request.getRequestDispatcher(newUrl).forward(request, response);
                } catch (Exception ex) {
                    log.error(ex.toString());
                }
            }
        }
    }

    /**
     * Обработка HTTP запроса методом GET.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Обработка HTTP запроса методом POST.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Освобождение ресурсов и закрытие пула соединений
     */
    @Override
    public void destroy() {
        try {
            conPool.shutdownConnection();
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        super.destroy();
    }
}