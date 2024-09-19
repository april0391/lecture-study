package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "INSERT_MEMBER")
    private String createdBy;

    private LocalDateTime createdDate;

    @Column(name = "UPDATE_MEMBER")
    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

}
