package com.example.actuatordemo.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean status = getStatus();
        Health.Builder builder = Health.unknown();
        if (status) {
            return builder
                    .up()
                    .withDetail("key1", "value1")
                    .withDetail("key2", "value2")
                    .build();
        }
        return builder
                .down()
                .withDetail("key3", "value3")
                .withDetail("key4", "value4")
                .build();
    }

    boolean getStatus() {
        return true;
    }
}
