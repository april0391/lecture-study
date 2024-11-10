package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns =
        @JoinColumn(name = "member_id")
    )
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "address")
    private Set<Address> addressHistory = new HashSet<>();

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, Team team) {
        this.username = username;
    }
}
