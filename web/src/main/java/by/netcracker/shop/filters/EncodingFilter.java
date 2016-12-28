package by.netcracker.shop.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private Logger logger = Logger.getLogger(EncodingFilter.class);
    private String initParamEncoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        initParamEncoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String requestEncoding = req.getCharacterEncoding();
        if (initParamEncoding != null && !initParamEncoding.equalsIgnoreCase(requestEncoding)) {
            req.setCharacterEncoding(initParamEncoding);
            resp.setCharacterEncoding(initParamEncoding);
        }
        resp.setContentType("UTF-8");
        logger.debug("CharacterEncoding was set");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
