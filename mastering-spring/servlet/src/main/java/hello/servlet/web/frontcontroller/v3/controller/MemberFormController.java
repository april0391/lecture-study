package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;

import java.io.IOException;
import java.util.Map;

public class MemberFormController implements ControllerV3 {

    @Override
    public ModelAndView process(Map<String, String> paramMap) throws ServletException, IOException {
        return new ModelAndView("new-form", Map.of());
    }
}
