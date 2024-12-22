package com.example.usingwell.threadpool;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class ThreadLocalCopyTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> map = MDC.getCopyOfContextMap();
        return () -> {
            if (map != null) {
                MDC.setContextMap(map);
            }
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
