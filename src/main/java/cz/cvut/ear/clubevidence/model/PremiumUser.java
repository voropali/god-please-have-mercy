package cz.cvut.ear.clubevidence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumUser extends User{
}
