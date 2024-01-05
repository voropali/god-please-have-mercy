package cz.cvut.ear.clubevidence.model;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Training")
public class Training extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private LocalTime time;
    @Basic(optional = false)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @Basic(optional = false)
    @Column(nullable = false)
    private String type;
    @ManyToMany
    @OrderBy("username")
    @JoinTable(name = "training_member")
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
