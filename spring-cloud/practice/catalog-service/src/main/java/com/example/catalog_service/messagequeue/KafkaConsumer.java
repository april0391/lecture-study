package com.example.catalog_service.messagequeue;

import com.example.catalog_service.jpa.CatalogEntity;
import com.example.catalog_service.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("kafka message={}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        try {
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String productId = (String) map.get("productId");
        CatalogEntity entity = catalogRepository.findByProductId(productId);
        if (entity != null) {
            Integer qty = (Integer) map.get("qty");
            entity.setStock(entity.getStock() - qty);
//            catalogRepository.save(entity);
        }

    }
}
