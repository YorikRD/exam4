package tourism;

import Dao.ClimberDao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupClimbers extends PrimeID{
    @ManyToOne(fetch = FetchType.LAZY)
    private Mountain mountain;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private Set<Climber> members;
    @Column
    private boolean availableToJoin;
    @Column
    private LocalDate start;

    private int lengthDays;

    public GroupClimbers() {
    }

    public GroupClimbers(Mountain mountain, boolean availableToJoin, LocalDate start, int lengthDays) {
       setMountain(mountain);
        this.availableToJoin = availableToJoin;
        this.start = start;
        setLengthDays(lengthDays);
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
        ClimberDao cldao = new ClimberDao();
        for (Climber member : members) {
            if(member.getId()==0&& member!= cldao.getByPk(0)){
              Set<GroupClimbers> newL = member.getGroups();
              newL.add(this);
              member.setGroups(newL);
            }
            Set<GroupClimbers> newL = cldao.getByPk(member.getId()).getGroups();
            newL.add(this);
            member.setGroupsChecked(newL);

        }

        this.members = members;

    }

    public boolean isAvailableToJoin() {
        return availableToJoin;
    }

    public void setAvailableToJoin(boolean availableToJoin) {
        this.availableToJoin = availableToJoin;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
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

    @Override
    public String toString() {
        return  '\n'+"GroupClimbers{" +
                "mountain=" + mountain.getId()+" "+mountain.getName()+
                ", availableToJoin=" + availableToJoin +
                ", start=" + start +
                ", lengthDays=" + lengthDays +" total members " +members.size()+
                '}';
    }
}
