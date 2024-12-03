package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.View;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", value = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> handlerMapping = new HashMap<>();
    private final ViewResolver viewResolver = new ViewResolver();

    public FrontControllerServletV3() {
        handlerMapping.put("/front-controller/v3/members/new-form", new MemberFormController());
        handlerMapping.put("/front-controller/v3/members/save", new MemberSaveController());
        handlerMapping.put("/front-controller/v3/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = handlerMapping.get(requestURI);

        if (controllerV3 == null) {
            response.setStatus(404);
            return;
        }
        Map<String, String> paramMap = paramMapInit(request);

        ModelAndView modelAndView = controllerV3.process(paramMap);
        String viewPath = viewResolver.resolve(modelAndView.getViewName());

        View view = new View(viewPath);
        view.render(modelAndView.getModel(), request, response);
    }

    private Map<String, String> paramMapInit(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(name -> paramMap.put(name, request.getParameter(name)));
        return paramMap;
    }

}
