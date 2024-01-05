package cz.cvut.ear.clubevidence.model;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Competition")
public class Competition extends AbstractEntity{
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false)
    private Date date;
    @Basic(optional = false)
    @Column(nullable = false)
    private String address;
    @ManyToMany
    @OrderBy("username")
    @JoinTable(name = "competition_participants")
    private List<User> participants;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
