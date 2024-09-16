package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true, length = 10) // DDL 생성 기능에만 영향
    @Column(name = "name", updatable = false, nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
