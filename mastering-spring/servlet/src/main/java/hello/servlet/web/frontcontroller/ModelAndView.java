package hello.servlet.web.frontcontroller;

import lombok.Data;

import java.util.Map;

@Data
public class ModelAndView {
    private String viewName;
    private final Map<String, Object> model;

    public ModelAndView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }
}
