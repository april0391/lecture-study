package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Schema schema = buildSchema();

    public OrderDto send(String topic, OrderDto orderDto) {
        Payload payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .product_id(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unit_price(orderDto.getUnitPrice())
                .total_price(orderDto.getTotalPrice())
                .build();
        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema, payload);
        String jsonInString = "";
        try {
            jsonInString = objectMapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice: " + kafkaOrderDto);

        return orderDto;
    }

    private Schema buildSchema() {
        List<Field> fields = List.of(
                new Field("string", true, "order_id"),
                new Field("string", true, "user_id"),
                new Field("string", true, "product_id"),
                new Field("int32", true, "qty"),
                new Field("int32", true, "unit_price"),
                new Field("int32", true, "total_price")
        );
        return Schema.builder()
                .type("struct")
                .fields(fields)
                .optional(false)
                .name("orders")
                .build();
    }

}
