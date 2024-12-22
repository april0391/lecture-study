package com.example.userservice.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final Environment env;

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> null;  // 400 오류는 특별한 처리 없이 넘어간다.
            case 404 -> {
                if (methodKey.contains("getOrders")) {
                    String msg = env.getProperty("order_service.exception.orders_is_empty");
                    yield new ResponseStatusException(HttpStatus.valueOf(response.status()), msg);
                }
                yield null;  // 다른 404 오류에는 기본 처리 없이 null을 반환
            }
            default -> new Exception(response.reason());  // 그 외 상태 코드에 대해서는 기본적으로 Exception을 발생시킨다.
        };
    }
}
