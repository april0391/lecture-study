package hello.login.web.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hello.login.domain.JwtConst;
import hello.login.web.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JwtLoginController implements LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginForm form, HttpServletResponse response) {
        Member member = loginService.login(form.getLoginId(), form.getPassword());
        System.out.println("member = " + member);
        String jwt = JWT.create()
                .withSubject("login")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .withClaim("id", member.getId())
                .withClaim("name", member.getName())
                .sign(Algorithm.HMAC256(JwtConst.SECRET));
//        response.setHeader(JwtConst.HEADER, JwtConst.PREFIX + jwt);
        Cookie authorization = new Cookie(JwtConst.HEADER, jwt);
        authorization.setMaxAge(60 * 10);
        response.addCookie(authorization);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다."); return "login/loginForm";
        }
        //로그인 성공 처리
        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
        String jwt = JWT.create()
                .withSubject("login")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .withClaim("id", loginMember.getId())
                .withClaim("name", loginMember.getName())
                .sign(Algorithm.HMAC256(JwtConst.SECRET));
        Cookie idCookie = new Cookie("memberId", jwt);
        response.addCookie(idCookie);
        return "redirect:/";
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public String handleLoginFail(IllegalArgumentException e, Model model) {
        // 새로운 LoginForm 객체 생성
        LoginForm loginForm = new LoginForm();

        // BindingResult 생성 후 글로벌 오류 추가
        BindingResult bindingResult = new BeanPropertyBindingResult(loginForm, "loginForm");
        bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");

        // 모델에 BindingResult와 loginForm 추가
        model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "loginForm", bindingResult);
        model.addAttribute("loginForm", loginForm);
        return "login/loginForm";
    }

    @ExceptionHandler(BindException.class)
    public String handleLoginFail(BindException e, Model model) {
        model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "loginForm", e.getBindingResult());
        return "login/loginForm";
    }
}
