package tech.minesoft.minesite.core.config;

import tech.minesoft.minesite.core.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Order(1)
@Component
public class ParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logParam(WebUtils.getRequest());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void logParam(HttpServletRequest request) {
        //输出参数
        StringBuilder result = new StringBuilder("\n");

        String method = request.getMethod();
        result.append(method);

        result.append(" | ");

        String inComeUrI = request.getRequestURI();

        result.append(inComeUrI).append(" | ");

        result.append("\n");

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String element = parameterNames.nextElement();
            result
                    .append(element)
                    .append(":")
                    .append(request.getParameter(element))
                    .append("\n");
        }

        log.info(result.toString());
    }
}
