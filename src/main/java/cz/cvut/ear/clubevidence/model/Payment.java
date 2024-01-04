package cz.cvut.ear.clubevidence.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "Payment")
@NamedQueries({
        @NamedQuery(name = "PaymentsRecord", query = "SELECT p FROM Payment p WHERE p.member = :username")})
public class Payment extends AbstractEntity{
    @Column
    private LocalDateTime paymentTime;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User noted;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User member;
    @Basic(optional = false)
    @Column(nullable = false)
    private int sum;

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime payed) {
        this.paymentTime = payed;
    }

    public User getNoted() {
        return noted;
    }

    public void setNoted(User noted) {
        this.noted = noted;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
