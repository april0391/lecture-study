package hello.servlet.web.frontcontroller.v5.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.View;
import hello.servlet.web.frontcontroller.ViewResolver;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormController;
import hello.servlet.web.frontcontroller.v3.controller.MemberListController;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveController;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", value = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMapping = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();
    private final ViewResolver viewResolver = new ViewResolver();

    public FrontControllerServletV5() {
        handlerMapping.put("/front-controller/v5/v3/members/new-form", new MemberFormController());
        handlerMapping.put("/front-controller/v5/v3/members/save", new MemberSaveController());
        handlerMapping.put("/front-controller/v5/v3/members", new MemberListController());

        handlerMapping.put("/front-controller/v5/v4/members/new-form", new hello.servlet.web.frontcontroller.v4.controller.MemberFormController());
        handlerMapping.put("/front-controller/v5/v4/members/save", new hello.servlet.web.frontcontroller.v4.controller.MemberSaveController());
        handlerMapping.put("/front-controller/v5/v4/members", new hello.servlet.web.frontcontroller.v4.controller.MemberListController());

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);
        String viewPath = viewResolver.resolve(modelAndView.getViewName());

        View view = new View(viewPath);
        view.render(modelAndView.getModel(), request, response);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter temp : handlerAdapters) {
            if (temp.supports(handler)) {
                return temp;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMapping.get(requestURI);
    }

    private Map<String, String> paramMapInit(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(name -> paramMap.put(name, request.getParameter(name)));
        return paramMap;
    }

}
