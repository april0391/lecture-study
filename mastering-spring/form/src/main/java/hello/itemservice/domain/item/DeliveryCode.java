package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeliveryCode {
    private String code;
    private String displayName;

    public DeliveryCode(String code) {
        this.code = code;
    }
}
