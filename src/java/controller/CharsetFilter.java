package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ������ ������� ����
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class CharsetFilter implements Filter {

    private String encoding;

    /**
     * ����� ��������� ������������� ������� � ��������� ��������� ��-���������
     * UTF-8
     *
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    /**
     * �������� ����� �������
     *
     * @param request ������ ��������
     * @param response ����� ��������
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    /**
     * ����� ����������� �������
     */
    public void destroy() {
    }
}
