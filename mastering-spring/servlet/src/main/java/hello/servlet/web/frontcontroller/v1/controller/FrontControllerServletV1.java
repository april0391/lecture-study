package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", value = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> handlerMapping = new HashMap<>();

    public FrontControllerServletV1() {
        handlerMapping.put("/front-controller/v1/members/new-form", new MemberFormController());
        handlerMapping.put("/front-controller/v1/members/save", new MemberSaveController());
        handlerMapping.put("/front-controller/v1/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV1 controllerV1 = handlerMapping.get(requestURI);

        if (controllerV1 == null) {
            response.setStatus(404);
            return;
        }

        controllerV1.process(request, response);
    }

}
