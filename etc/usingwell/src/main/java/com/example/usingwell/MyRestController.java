package com.example.usingwell;

import com.example.usingwell.objectmapper.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@RestController
public class MyRestController {

    private final RestTemplate restTemplate;
    private final RestClient restClient;

    @GetMapping("/object-mapper")
    public Item getItem() {
        Item item = new Item();
        item.setId("a1");
        item.setName("hello");
        item.setLocalDateTime(LocalDateTime.now());
        item.setLocalDate(LocalDate.now());
        return item;
    }

    @GetMapping("/target")
    public String target() {
        log.info("target");
        return "target";
    }

    @GetMapping("/rest-template")
    public String restTemplate() {
        String body = restTemplate.getForEntity("http://localhost:8080/target", String.class)
                .getBody();
        log.info("body={}", body);
        return body;
    }

    @GetMapping("/rest-client")
    public String restClient() {
        String body = restClient.get()
                .uri("http://localhost:8080/target")
                .retrieve()
                .body(String.class);
        log.info("body={}", body);
        return body;
    }
}
