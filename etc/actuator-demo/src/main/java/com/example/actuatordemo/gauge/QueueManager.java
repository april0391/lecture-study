package com.example.actuatordemo.gauge;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class QueueManager {

    public long getQueueSize() {
        return new Random().nextLong(1000);
    }
}
