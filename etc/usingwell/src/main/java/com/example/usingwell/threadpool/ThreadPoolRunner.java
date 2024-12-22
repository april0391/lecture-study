package com.example.usingwell.threadpool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ThreadPoolRunner implements ApplicationRunner {

    private final ThreadPoolTarget target;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run...");
        target.async();
    }
}
