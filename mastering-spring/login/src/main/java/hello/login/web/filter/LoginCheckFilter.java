package hello.login.web.filter;

import hello.login.web.member.Member;
import hello.login.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter extends HttpFilter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        try {
            log.info("인증 체크 필터 시작 [{}]", requestURI);
            if (!isLoginCheckPath(requestURI)) {
                log.info("화이트 리스트 경로 요청 [{}]", requestURI);
                chain.doFilter(request, response);
                return;
            }
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                log.info("로그인 체크 적용 경로 미인증 사용자 요청 [{}]", requestURI);
                response.sendRedirect("/login?redirectURI=" + requestURI);
                return;
            }
            log.info("로그인 체크 적용 경로 인증 사용자 요청 [{}]", requestURI);
            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;

        } finally {
            log.info("인증 체크 필터 종료 [{}]", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크 X
     */
    private boolean isLoginCheckPath(String requestURI) {
        /*for (String s : whitelist) {
            if (s.equals(requestURI)) {
                return false;
            }
        }
        return true;*/
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
