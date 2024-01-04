package cz.cvut.ear.clubevidence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Club")
public class Club extends AbstractEntity{
    private List<User> members;
    private List <Training> trainings;

    private List<Competition> competitions;

    @OneToMany
    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @OneToMany
    public List<Training> getTrenings() {
        return trainings;
    }

    public void setTrenings(List<Training> trainings) {
        this.trainings = trainings;
    }

    @OneToMany
    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
