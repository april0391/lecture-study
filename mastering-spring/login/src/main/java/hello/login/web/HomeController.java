package hello.login.web;

import hello.login.domain.member.MemberRepository;
import hello.login.web.argunemtresolver.Login;
import hello.login.web.member.Member;
import hello.login.web.session.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("loginId"))
                .findFirst()
                .ifPresent(loginCookie -> {
                            Member member = memberRepository.findById(Long.parseLong(loginCookie.getValue())).orElseThrow();
                            model.addAttribute("loginId", member.getName());
                });
        return "home";
    }

//    @GetMapping
    public String homeV2(HttpServletRequest request, Model model) {
        Member member = (Member) sessionManager.getSession(request);
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping
    public String homeV3(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping
    public String homeV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println("session = " + session);
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping
    public String homeV4ArgumentResolver(@Login Member loginMember, Model model, HttpServletRequest request) {
        System.out.println("HomeController.homeV4ArgumentResolver");
//        Member member = (Member) request.getAttribute("member");
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}