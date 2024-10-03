package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String  requestParamV2(@RequestParam("username") String a, @RequestParam("age") int b) throws IOException {
        log.info("username={}, age={}", a, b);
        return "ok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String  requestParamV3(@RequestParam String username, @RequestParam int age) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String  requestParamV4(String username, int age) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-required")
    @ResponseBody
    public String  requestParamRequired(@RequestParam(required = false) String username, @RequestParam(required = false) int age) throws IOException {
//        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String  requestParamDefault(@RequestParam(required = false, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") Integer age) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody
    public String  requestParamMap(@RequestParam Map<String, Object> paramMap) throws IOException {
        Object username = paramMap.get("username");
        Object age = paramMap.get("age");
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-map-noa")
    @ResponseBody
    public String  requestParamMapNoa(Map<String, Object> paramMap) throws IOException {
        Object username = paramMap.get("username");
        Object age = paramMap.get("age");
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/model-attribute-v1")
    @ResponseBody
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "response/model-attribute";
    }

}