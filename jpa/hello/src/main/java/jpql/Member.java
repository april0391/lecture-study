package jpql;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;


    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
