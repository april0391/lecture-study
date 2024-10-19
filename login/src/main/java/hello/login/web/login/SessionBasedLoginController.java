package hello.login.web.login;

import hello.login.web.member.Member;
import hello.login.web.session.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
//@Controller
public class SessionBasedLoginController implements LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @Override
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

//    @PostMapping("/login")
    public String loginV1(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        HttpServletResponse response) {
        Member member;
        try {
            member = loginService.login(form.getLoginId(), form.getPassword());
        } catch (IllegalArgumentException e) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        sessionManager.createSession(member, response);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, HttpSession session,
                          @RequestParam(defaultValue = "/") String redirectURI) {
        Member member = loginService.login(form.getLoginId(), form.getPassword());
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:" + redirectURI;
    }

/*
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }
*/

    @PostMapping("/logout")
    public String logoutV2(HttpSession session) {
        session.invalidate();
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
