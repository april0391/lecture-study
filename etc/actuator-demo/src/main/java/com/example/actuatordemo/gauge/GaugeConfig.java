package com.example.actuatordemo.gauge;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GaugeConfig {

//    @Bean
    public Gauge queueSizeGauge(MeterRegistry registry, QueueManager queueManager) {
        return Gauge.builder("my.queue.size", queueManager, QueueManager::getQueueSize)
                .description("Tracks the size of the queue")
                .register(registry);
    }
}
