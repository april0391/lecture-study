package jpabook.jpashop.domain.inheritance;

import jakarta.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
