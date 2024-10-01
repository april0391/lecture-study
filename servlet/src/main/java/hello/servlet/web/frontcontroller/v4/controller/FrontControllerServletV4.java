package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.View;
import hello.servlet.web.frontcontroller.ViewResolver;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", value = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private final Map<String, ControllerV4> handlerMapping = new HashMap<>();
    private final ViewResolver viewResolver = new ViewResolver();

    public FrontControllerServletV4() {
        handlerMapping.put("/front-controller/v4/members/new-form", new MemberFormController());
        handlerMapping.put("/front-controller/v4/members/save", new MemberSaveController());
        handlerMapping.put("/front-controller/v4/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controllerV4 = handlerMapping.get(requestURI);

        if (controllerV4 == null) {
            response.setStatus(404);
            return;
        }
        Map<String, String> paramMap = paramMapInit(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controllerV4.process(paramMap, model);
        String viewPath = viewResolver.resolve(viewName);

        View view = new View(viewPath);
        view.render(model, request, response);
    }

    private Map<String, String> paramMapInit(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(name -> paramMap.put(name, request.getParameter(name)));
        return paramMap;
    }

}
