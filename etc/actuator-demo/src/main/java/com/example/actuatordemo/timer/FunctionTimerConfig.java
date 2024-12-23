package com.example.actuatordemo.timer;

import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FunctionTimerConfig {

    @Bean
    public FunctionTimer myFunctionTimer(MyTimerManager manager, MeterRegistry registry) {
        return FunctionTimer.builder(
                "my.functiontimer",
                manager,
                value -> value.getCount(),
                value -> value.getTotalTime(),
                TimeUnit.SECONDS
        ).register(registry);
    }
}
