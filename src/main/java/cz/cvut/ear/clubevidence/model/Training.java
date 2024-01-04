package cz.cvut.ear.clubevidence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Training")
public class Training extends AbstractEntity{
    private LocalTime time;
    private String type;
    @ManyToMany
    private List<User> members;
    public Training(LocalTime time, String type) {
        this.time = time;
        this.type = type;
        this.members = new ArrayList<>();
    }

    public Training() {

    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
