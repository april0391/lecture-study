package com.example.actuatordemo.timer;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/timer")
public class TimerController {

    private final Timer myTimer;
    private final MeterRegistry meterRegistry;

    public TimerController(@Qualifier("myTimer") Timer myTimer, MeterRegistry meterRegistry) {
        this.myTimer = myTimer;
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/timer")
    public String timer() {
        myTimer.record(() -> {
            System.out.println("로직 수행");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return "ok";
    }

    @GetMapping("/timer2")
    public void timer2() {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            long l = new Random().nextLong(2000);
            Thread.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sample.stop(meterRegistry.timer("my.timer2"));
    }

    @Timed("my.timer3")
    @GetMapping("/timer3")
    public void timer3() throws InterruptedException {
        long l = new Random().nextLong(2000);
        Thread.sleep(l);
    }

}
