package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@NamedQuery(name = "Member.findByUsername", query = "SELECT m FROM Member m WHERE m.username = :username")
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @Embedded
    private Address homeAddress;

//    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    /*@ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns =
        @JoinColumn(name = "member_id")
    )
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "address")
    private Set<Address> addressHistory = new HashSet<>();*/

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, Team team) {
        this.username = username;
    }
}
