package tourism;


import Dao.ClimberDao;
import auxilary.SolitaryManager;
import staticGen.StaticStrings;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Climber extends PrimeID {
    @Column(nullable = false, length = 42)
    private String name;
    @Column(nullable = false, length = 120)
    private String adress;
    @Column(nullable = false)
    private int age;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
    private Set<GroupClimbers> groups;

    public Climber(String name, String adress, int age) {
        setName(name);
        setAdress(adress);
        setAge(age);
        this.groups = new HashSet<GroupClimbers>();
    }

    public Climber() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals(null) || name.length() < 3) {
            throw new IllegalArgumentException("Name must be longer then 3 symbols");
        }
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        if (adress.equals(null) || adress.length() < 5) {
            throw new IllegalArgumentException("Name must be longer then 4 symbols");
        }
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        Objects.requireNonNull(age);
        if (age < 18) {
            throw new IllegalArgumentException("The climbers age must be at least 18 years old");
        }
        this.age = age;
    }

    public Set<GroupClimbers> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupClimbers> groups) {
       setGroupsChecked(groups);
    }

    public boolean setGroupsChecked (Set<GroupClimbers> groups) {
        ClimberDao tempDao = new ClimberDao();
        if (this.getId() == 0 && tempDao.getByPk(0) != this) {
            this.groups = groups;
            return false;
        }
        Set<GroupClimbers> groupsCurrient = tempDao.getByPk(this.getId()).getGroups();
        for (GroupClimbers groupClimbers : groupsCurrient) {
            LocalDate start1 = groupClimbers.getStart();
            LocalDate finish1 = groupClimbers.getStart().plusDays(groupClimbers.getLengthDays());
            for (GroupClimbers group : groups) {
                LocalDate start2 =group.getStart();
                LocalDate finish2 = start2.plusDays(group.getLengthDays());
                if (start2.isAfter(start1)&&start2.isBefore(finish1)) return false;
                if (finish2.isAfter(start1)&&start2.isBefore(finish1)) return false;
            }
        }
        this.groups =groups;
        return true;
    }

    private String groupsIdandMountain() {
        StringBuilder stringBuilder = new StringBuilder(" Groups");
        for (GroupClimbers group : groups) {
            stringBuilder.append(" " + group.getId() + " to mountain: " + group.getMountain() + '\n');
        }
        String result = stringBuilder.toString();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Climber climber = (Climber) o;

        if (age != climber.age) return false;
        if (name != null ? !name.equals(climber.name) : climber.name != null) return false;
        if (adress != null ? !adress.equals(climber.adress) : climber.adress != null) return false;
        return groups != null ? groups.equals(climber.groups) : climber.groups == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return '\n' + "Climber{" + " Id of: " + getId() + " " +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", age=" + age + groupsIdandMountain() +
                '}';
    }


}
