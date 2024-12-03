package domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    @PrePersist
    public void prePersist() {
        this.createdBy = "admin";
        this.createdDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedBy = "admin";
        this.lastModifiedDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }
}
