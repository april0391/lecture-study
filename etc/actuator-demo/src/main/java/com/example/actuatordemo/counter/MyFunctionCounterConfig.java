package com.example.actuatordemo.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MyFunctionCounterConfig {

    private final MyHttpRequestManagerWithoutMicrometer manager;
    private final MeterRegistry registry;

    @PostConstruct
    void init() {
        FunctionCounter.builder("my.function.counter", manager, m -> m.getCount())
                .register(registry);
    }
}
