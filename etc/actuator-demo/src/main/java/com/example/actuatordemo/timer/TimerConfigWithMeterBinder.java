package com.example.actuatordemo.timer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerConfigWithMeterBinder {

    @Bean
    public MeterBinder myTimerMeterBinder() {
        return new MeterBinder() {
            @Override
            public void bindTo(MeterRegistry meterRegistry) {

            }
        };
    }
}
