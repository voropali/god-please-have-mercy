package cz.cvut.ear.clubevidence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Training")
public class Training extends AbstractEntity{
    private LocalTime time;
    private DayOfWeek dayOfWeek;
    private String type;
    @ManyToMany
    private List<User> members;

    public Training(LocalTime time, DayOfWeek dayOfWeek, String type){
        this.time=time;
        this.dayOfWeek=dayOfWeek;
        this.type=type;
    }

    public Training() {

    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
