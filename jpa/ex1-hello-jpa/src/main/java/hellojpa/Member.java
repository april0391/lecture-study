package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Table(name = "mbr")
public class Member {

    @Id
    private Long id;

//    @Column(unique = true, length = 10) // DDL 생성 기능에만 영향
    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

}
