package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
