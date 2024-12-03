package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommandLineV1 {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String arg : args) {
            String[] split = arg.split("=");
            map.put(split[0], split[1]);
//            log.info("arg {}", arg);
        }

        for (String s : map.keySet()) {
            log.info("arg {}={}", s, map.get(s));
        }
    }
}
