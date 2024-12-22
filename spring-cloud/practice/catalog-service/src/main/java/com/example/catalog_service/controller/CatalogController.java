package com.example.catalog_service.controller;

import com.example.catalog_service.jpa.CatalogEntity;
import com.example.catalog_service.service.CatalogService;
import com.example.catalog_service.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getUsers() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(userEntity -> {
            ResponseCatalog responseCatalog = new ModelMapper().map(userEntity, ResponseCatalog.class);
            result.add(responseCatalog);
        });
        return ResponseEntity.status(200)
                .body(result);
    }

}
