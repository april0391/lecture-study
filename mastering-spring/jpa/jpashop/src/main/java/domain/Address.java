package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
