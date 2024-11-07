package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER 가 default
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    private Locker locker;

    @ManyToMany
    @JoinTable(name = "member_product")
    private List<Product> products = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, Team team) {
        this.username = username;
//        this.team = team;
    }
}
