package com.example.actuatordemo.counter;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class MyHttpRequestManagerWithoutMicrometer {

    private final AtomicLong count = new AtomicLong();

    public long getCount() {
        return count.get();
    }

    public void increase() {
        count.incrementAndGet();
    }
}
