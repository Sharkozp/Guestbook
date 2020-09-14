package om.dykyi.controller;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр русских букв
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class CharsetFilter implements Filter {

    private String encoding;

    /**
     * Метод первичной инициализации фильтра и установки кодировки по-умолчанию
     * UTF-8
     *
     * @param config
     */
    public void init(FilterConfig config) {
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
