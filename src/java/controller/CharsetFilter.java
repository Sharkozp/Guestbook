package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Фильтр русских букв
 *
 * @author Дикий Александр Николаевич
 * @version 1.0
 */
public class CharsetFilter implements Filter {

    private String encoding;

    /**
     * Метод первичной инициализации фильтра и установки кодировки по-умолчанию
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
     * Основной метод фильтра
     *
     * @param request  запрос сервлета
     * @param response ответ сервлета
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    /**
     * Метод освобождает ресурсы
     */
    public void destroy() {
    }
}
