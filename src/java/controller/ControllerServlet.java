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
 * ControllerServlet - ������ ���������� � ������ MVC. ����� ControllerServlet -
 * ������������ ��� ������� ������������� � ������������ �� ��������������� JSP
 * ��������, ����������� ������� ������ �� JSP ���������.
 *
 * @version 1.1
 * @author ����� ��������� ����������
 */
public class ControllerServlet extends HttpServlet {

    /**
     * ����������� ������ ControllerServlet.class
     */
    public static final Logger log = Logger.getLogger(ControllerServlet.class);
    protected ActionFactory factory = new ActionFactory();
    private DataSource dataSource;

    /**
     * ������������� ��������-�����������. ����������� �������� ���� ����������.
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
     * ��������� �������� ��� ����� ������� HTTP (�� GET � �� POST).
     *
     * @param request ������ � ��������
     * @param response ����� ��������
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
     * ��������� HTTP ������� ������� GET.
     *
     * @param request ������ � ��������
     * @param response ����� ��������
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * ��������� HTTP ������� ������� POST.
     *
     * @param request ������ � ��������
     * @param response ����� ��������
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * ������������ �������� � �������� ���� ����������
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