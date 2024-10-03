package hello.springmvc.basic.request;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
//@RestController
@Controller
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
    ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        String cookieValue = "cookieTest";
        Cookie cookie1 = new Cookie("myCookie",cookieValue);
        cookie1.setMaxAge(600);
        cookie1.setHttpOnly(true);
        cookie1.setValue("newValue");
        cookie1.setAttribute("test", "testValue");
        response.addCookie(cookie1);

        session.setAttribute("test", "session test");

        return "ok";
    }

    @GetMapping("/headers-cookie")
    public String headersCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie findCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("myCookie".equals(name)) {
                    findCookie = cookie;
                    break;
                }
            }
        }
        Map<String, String> attributes = findCookie.getAttributes();
        attributes.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                log.info("s, s2 = {}, {}", s, s2);
            }
        });

        return "ok";
    }

    @GetMapping("headers-session")
    public String session(@SessionAttribute(name = "test") String abc) {
        System.out.println("test = " + abc);
        return null;
    }

    @GetMapping("/submit")
    public String submitForm(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("userId", 123); // URL에 추가
        redirectAttributes.addFlashAttribute("message", "Submission successful!"); // Flash attribute로 세션에 추가
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    @ResponseBody
    public String confirmation(Model model) {
        // 플래시 속성 가져오기
        if (model.containsAttribute("message")) {
            String message = (String) model.getAttribute("message");
            System.out.println("Flash message: " + message);
        }
        return "ok";
    }

}
