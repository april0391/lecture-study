package hello.itemservice.domain.item;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private Boolean open;  // 판매 여부

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item_regions", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "region")
    private List<String> regions = new ArrayList<>();  // 등록 지역

    private ItemType itemType;  // 상품 종류

    @Embedded
    private DeliveryCode deliveryCode;  // 배송 방식

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
