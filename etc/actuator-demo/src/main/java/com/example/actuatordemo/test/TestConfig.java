package com.example.actuatordemo.test;

import com.example.actuatordemo.gauge.QueueManager;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.ToDoubleFunction;

@RequiredArgsConstructor
@Configuration
public class TestConfig {

    private final MeterRegistry meterRegistry;
    private final Random random = new Random();

    @Bean
    public Gauge testGauge(MeterRegistry registry) {
        return Gauge.builder("test.gauge", () -> random.nextLong(100))
                .description("Tracks the size of the queue")
                .register(registry);
    }

    @Bean
    public Timer testTimer() {
        return Timer.builder("test.timer")
                .register(meterRegistry);
    }

}
