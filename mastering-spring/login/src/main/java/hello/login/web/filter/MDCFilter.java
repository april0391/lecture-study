package hello.login.web.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

//@Component
public class MDCFilter extends HttpFilter {

    private final String REQUEST_ID = "requestId";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put(REQUEST_ID, UUID.randomUUID().toString().substring(0, 8));
        chain.doFilter(request, response);
        MDC.remove(REQUEST_ID);
    }

}
