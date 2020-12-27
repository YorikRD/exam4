package tourism;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupClimbers extends PrimeID{
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Mountain mountain;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Climber> members;
    @Column
    private boolean availableToJoin;
    @Column
    @Temporal(TemporalType.DATE)
    private Date start;

    private int lengthDays;

    public GroupClimbers() {
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        Objects.requireNonNull(mountain,"The mountain must be not Null");
        this.mountain = mountain;
    }

    public Set<Climber> getMembers() {
        return members;
    }

    public void setMembers(Set<Climber> members) {
        this.members = members;
    }

    public boolean isAvailableToJoin() {
        return availableToJoin;
    }

    public void setAvailableToJoin(boolean availableToJoin) {
        this.availableToJoin = availableToJoin;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getLengthDays() {
        return lengthDays;
    }

    public void setLengthDays(int lengthDays) {
        if (lengthDays <1){
            throw new IllegalArgumentException("Length of groups journey mus be at least one day");
        }
        this.lengthDays = lengthDays;
    }
}
