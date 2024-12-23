package com.example.actuatordemo.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyCustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", new String[]{"a", "b", "c"});
        map.put("key3", Map.of("subKey1", "subValue1", "subKey2", "subValue2"));
        builder.withDetail("myCustomInfo", map);
    }
}
