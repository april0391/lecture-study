package hello.servlet.web.frontcontroller;

public class ViewResolver {

    private final String prefix = "/WEB-INF/views/";
    private final String suffix = ".jsp";

    public String resolve(String viewName) {
        return prefix + viewName + suffix;
    }
}
