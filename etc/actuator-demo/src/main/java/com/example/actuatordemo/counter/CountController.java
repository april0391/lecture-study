package com.example.actuatordemo.counter;

import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counter")
public class CountController {

    private final MyHttpRequestManager myHttpRequestManager;
    private final MyHttpRequestManagerWithoutMicrometer myManager;

    @Counted("my.counted.counter")
    @GetMapping("/req")
    public String req() {
        myHttpRequestManager.increase();
        myManager.increase();
        return "ok";
    }
}
