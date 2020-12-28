package tourism;


import Dao.ClimberDao;

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
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<GroupClimbers> groups;
    @Transient
    public static ClimberDao tempDao = new ClimberDao();

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

    /**
     * Checked method for altering climbers  Set<GroupClimbers> groups, avoid any others.
     * @param groupSet
     */
    public void setGroups(Set<GroupClimbers> groupSet) {
        if (this.getId() == 0 && tempDao.getByPk(0) != this) {
            this.groups = groupSet;
            return;
        }
        updategroupsFromBaze();
        for (GroupClimbers group : this.groups) {
            if (!groupSet.contains(group)) this.groups.remove(group);
        }
        for (GroupClimbers groupClimbers : groupSet) {
            IfIsValidForClimberAdd(groupClimbers);
        }
    }

    /**
     * Checking method for adding Group to Set<GroupClimbers> groups, is used by @setGroups
     * @param group - single group for adding to climber
     * @return true if group was succesfully added
     */
    public boolean IfIsValidForClimberAdd (GroupClimbers group){
        updategroupsFromBaze();
        LocalDate start2 = group.getStart();
        LocalDate finish2 = start2.plusDays(group.getLengthDays());
        for (GroupClimbers groupClimbers : this.groups) {
            LocalDate start1 = groupClimbers.getStart();
            LocalDate finish1 = groupClimbers.getStart().plusDays(groupClimbers.getLengthDays());
            if (start2.isAfter(start1) && start2.isBefore(finish1)) return false;
            if (finish2.isAfter(start1) && start2.isBefore(finish1)) return false;
        }
        this.groups.add(group);
        return true;
    }

    /**
     * Method used for keeping   Set<GroupClimbers> groups true for Entities not present in Base may cause problems
     * Is called only by checking for transient quality in methods of higher level.
     */
    private void updategroupsFromBaze(){
        Climber climber = tempDao.getByPk(this.getId());
        if (climber != null) {
            this.groups = climber.getGroups();
        }
    }

    /**
     * Intertnal method
     * @return  String representing groups partisipated by this climber
     */

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

    /**
     *
     * @return String representation  of Climber DeadLock safe
     */
    @Override
    public String toString() {
        return '\n' + "Climber{" + " Id of: " + getId() + " " +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", age=" + age + groupsIdandMountain() +
                '}';
    }


}
