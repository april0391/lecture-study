package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {

    @GetMapping("/error-ex")
    public void errorEx() {
        System.out.println("ServletExceptionController.errorEx");
        throw new RuntimeException();
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        System.out.println("ServletExceptionController.error404");
        response.sendError(404, "404오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        System.out.println("ServletExceptionController.error500");
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "500오류");
    }
}
