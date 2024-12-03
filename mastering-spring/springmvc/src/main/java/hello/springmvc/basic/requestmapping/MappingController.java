package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-basic-array"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath = {}", data);
        return "ok";
    }

    @GetMapping("/mapping/{id}/{username}")
    public String mappingPath2(@PathVariable String id, @PathVariable String username) {
        log.info("mappingPath = {}, {}", id, username);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(String mode) {
        log.info("mappingParam = {}", mode);
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(String mode) {
        log.info("mappingHeader = {}", mode);
        return "ok";
    }

    @GetMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(String mode) {
        log.info("mappingConsumes");
        return "ok";
    }

    @GetMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(String mode) {
        log.info("mappingProduces");
        return "ok";
    }


}
