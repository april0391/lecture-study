package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long teamId;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY) // LAZY 가 default
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
