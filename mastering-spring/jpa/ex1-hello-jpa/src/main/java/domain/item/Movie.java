package domain.item;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("movie")
public class Movie extends Item {

    private String director;
    private String actor;
}
