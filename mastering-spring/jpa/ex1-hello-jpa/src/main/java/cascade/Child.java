package cascade;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Child {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Parent parent;

}
