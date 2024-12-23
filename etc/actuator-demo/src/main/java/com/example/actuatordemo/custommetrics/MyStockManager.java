package com.example.actuatordemo.custommetrics;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class MyStockManager implements Supplier<Number> {

    private Integer stock = 100;

    public long getStockCount() {
        return stock--;
    }

    @Override
    public Number get() {
        return stock--;
    }
}
