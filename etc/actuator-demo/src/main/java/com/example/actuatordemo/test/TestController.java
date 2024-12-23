package com.example.actuatordemo.test;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final Timer testTimer;

    @Counted("test.count")
    @GetMapping("/count")
    public void count() {

    }

    @GetMapping("/timer")
    public void timer() {
        testTimer.record(() -> {
            try {
                Thread.sleep(new Random().nextLong(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
