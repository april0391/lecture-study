package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

//@Entity
@Table(name = "member_sequence")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50)
public class Member_sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

//    @Column(unique = true, length = 10) // DDL 생성 기능에만 영향
    @Column(name = "name", updatable = false, nullable = false)
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
