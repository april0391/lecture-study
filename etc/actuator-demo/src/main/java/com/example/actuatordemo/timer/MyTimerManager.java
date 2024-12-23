package com.example.actuatordemo.timer;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyTimerManager {

    private final List<Long> list = new ArrayList<>();
    private final Random random = new Random();
    private final long VALUE = 100;

    public long getCount() {
        long l = random.nextLong(VALUE);
        list.add(l);
        return l;
    }

    public long getTotalTime() {
        return list.stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public long getMax() {
        return list.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L);
    }
}
