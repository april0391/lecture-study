package hello.login.web.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hello.login.domain.JwtConst;
import hello.login.web.login.LoginService;
import hello.login.web.member.Member;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends HttpFilter {

    private final LoginService loginService;
    private static final String[] whitelist = {"/css/*"};

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JwtAuthorizationFilter.doFilter");
        String requestURI = request.getRequestURI();
        boolean loginCheckPath = isLoginCheckPath(requestURI);
        if (!loginCheckPath) {
            chain.doFilter(request, response);
            return;
        }
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(JwtConst.HEADER))
                .findAny()
                .orElse(null);
        if (cookie == null) {
            chain.doFilter(request, response);
            return;
        }
        String jwt = cookie.getValue();
        Long id = JWT.require(Algorithm.HMAC256(JwtConst.SECRET))
                .build()
                .verify(jwt)
                .getClaim("id")
                .asLong();
        Member findMember = loginService.findById(id);
        System.out.println("findMember = " + findMember);
        request.setAttribute("member", findMember);
        chain.doFilter(request, response);
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
