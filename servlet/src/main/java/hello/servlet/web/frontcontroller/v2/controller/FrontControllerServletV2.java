package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.View;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", value = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private final Map<String, ControllerV2> handlerMapping = new HashMap<>();

    public FrontControllerServletV2() {
        handlerMapping.put("/front-controller/v2/members/new-form", new MemberFormController());
        handlerMapping.put("/front-controller/v2/members/save", new MemberSaveController());
        handlerMapping.put("/front-controller/v2/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV2 controllerV2 = handlerMapping.get(requestURI);

        if (controllerV2 == null) {
            response.setStatus(404);
            return;
        }

        View view = controllerV2.process(request, response);
        view.render(request, response);
    }

}
