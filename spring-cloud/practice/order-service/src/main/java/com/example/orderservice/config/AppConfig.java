package com.example.orderservice.config;

import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

    private final ApplicationContext ac;
    private final HikariDataSource dataSource;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            String jdbcUrl = dataSource.getJdbcUrl();
            System.out.println("jdbcUrl = " + jdbcUrl);
        };
    }
}
