package hello.login.web.login;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface LoginController {

    String loginForm(@ModelAttribute("loginForm") LoginForm form);

}
