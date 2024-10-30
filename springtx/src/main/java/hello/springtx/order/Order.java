package hello.springtx.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "orders")
@Entity
public class Order {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String payStatus;

}
