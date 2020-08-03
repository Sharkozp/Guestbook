package controller;

import action.factory.Action;
import action.factory.ActionFactory;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

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
    private DataSource dataSource;

    /**
     * Инициализация сервлета-контроллера. Выполняется создания пула соединений.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        ConnectionPool conPool = ConnectionPool.getInstance();
        conPool.setProperties();
        dataSource = conPool.getDataSource();
    }

    /**
     * Обработка запросов для обоих методов HTTP (от GET и от POST).
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @throws ServletException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String command = request.getParameter("command");
        if (command != null || command.length() != 0) {
            Action action = factory.create(command);
            String url = action.perform(request, response, dataSource);
            if (url != null) {
                try {
                    String newUrl = "/WEB-INF/view/" + url;
                    request.getRequestDispatcher(newUrl).forward(request, response);
                } catch (Exception ex) {
                    log.error(ex.toString());
                }
            }
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Обработка HTTP запроса методом GET.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Обработка HTTP запроса методом POST.
     *
     * @param request  Запрос к сервлету
     * @param response Ответ сервлета
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Освобождение ресурсов и закрытие пула соединений
     */
    @Override
    public void destroy() {
        try {
            ConnectionPool.shutdownDataSource(dataSource);
        } catch (SQLException se) {
            log.error(se.getMessage());
        }
        super.destroy();
    }
}