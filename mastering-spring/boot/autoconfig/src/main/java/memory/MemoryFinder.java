package memory;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryFinder {

    public Memory get() {
        /*long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();*/
        long max = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        long total = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        long free = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        long used = total - free;
        return new Memory(used, max);
    }

    @PostConstruct
    public void init() {
        log.info("init memoryFinder");
    }
}
