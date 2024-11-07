package domain.item;

import domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Item extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
