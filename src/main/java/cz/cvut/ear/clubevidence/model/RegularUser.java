package cz.cvut.ear.clubevidence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("REGULAR")
public class RegularUser extends User{
}
