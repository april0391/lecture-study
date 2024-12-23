package com.example.actuatordemo.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfigWithMeterBinder {

    @Bean
    public MeterBinder myCounterWithMeterBinder(MyHttpRequestManagerWithoutMicrometer manager) {
        return registry -> FunctionCounter.builder("my.function.counter2", manager, m -> m.getCount())
                .register(registry);
    }
}
