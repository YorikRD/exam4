package tourism;


import staticGen.StaticStrings;

import javax.persistence.*;
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
        this.groups = groups;
    }

    private String groupsIdandMountain(){
        StringBuilder stringBuilder = new StringBuilder(" Groups");
        for (GroupClimbers group : groups) {
            stringBuilder.append(" "+group.getId()+" to mountain: "+group.getMountain()+'\n');
        }
        String result = stringBuilder.toString();
        return result;
    }

    @Override
    public String toString() {
        return '\n' + "Climber{" + " Id of: " + getId() + " " +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", age=" + age + groupsIdandMountain()+
                '}';
    }
}
