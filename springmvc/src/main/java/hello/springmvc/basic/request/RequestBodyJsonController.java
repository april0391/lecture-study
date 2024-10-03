package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public ResponseEntity<String> requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/request-body-json-v2")
    public ResponseEntity<String> requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/request-body-json-v3")
    public ResponseEntity<String> requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData={}", helloData);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/request-body-json-v4")
    public ResponseEntity<String> requestBodyJsonV4(HttpEntity<HelloData> data) throws IOException {
        HelloData helloData = data.getBody();
        log.info("helloData={}", helloData);
        return ResponseEntity.status(201).build();
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public ResponseEntity<HelloData> requestBodyJsonV5(@RequestBody HelloData helloData, HttpServletResponse response) throws IOException {
        log.info("helloData={}", helloData);
        response.setStatus(201);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(helloData);
    }

}
