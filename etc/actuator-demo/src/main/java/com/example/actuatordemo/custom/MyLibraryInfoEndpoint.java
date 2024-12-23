package com.example.actuatordemo.custom;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Slf4j
//@Endpoint
@WebEndpoint(id = "myLibraryInfo")
public class MyLibraryInfoEndpoint {

    @WriteOperation
    public void changeSomething(String name, boolean enableSomething) {
        log.info("name: {}, enableSomething: {}", name, enableSomething);
    }

    @ReadOperation
    public String getPathTest(@Selector(match = Selector.Match.ALL_REMAINING) String[] path) {
        log.info("path: {}", Arrays.asList(path));
        return "path: " + Arrays.asList(path);
    }

    @ReadOperation
    public List<LibraryInfo> getLibraryInfos(@Nullable String name, boolean includeVersion) {
        LibraryInfo libraryInfo1 = new LibraryInfo("logback", "1.0.0");
        LibraryInfo libraryInfo2 = new LibraryInfo("jackson", "2.0.0");

        List<LibraryInfo> list = Arrays.asList(libraryInfo1, libraryInfo2);
        if (name != null) {
            list = list.stream()
                    .filter(lib -> lib.getName().equals(name))
                    .toList();
        }
        if (!includeVersion) {
            list = list.stream()
                    .map(lib -> {
                        lib.setVersion(null);
                        return lib;
                    })
                    .toList();
        }
        return list;
    }
}
