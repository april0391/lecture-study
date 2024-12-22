package com.example.usingwell.threadpool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ThreadPoolController {

    private final ThreadPoolTarget threadPoolTarget;

    @GetMapping("/thread-pool")
    public String threadPool() {
        MDC.put("userId", "helloWorld");
        log.info("threadPool. userId: {}", MDC.get("userId"));
        threadPoolTarget.async();
        return "ok";
    }
}
