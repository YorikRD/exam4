package tourism;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "climbers")
public class Climber extends PrimeID {
    @NotNull
    @Size(min = 4,max = 42)
    @Column(nullable = false,length = 42)
    private String  name;
    @NotNull
    @Size(min = 5,max = 120)
    @Column(nullable = false,length = 120)
    private String adress;
    @NotNull
    @Min(4)
    @Column(nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
    private Set<GroupClimbers> groups;

    public Climber(@NotNull @Size(min = 4, max = 42) String name, @NotNull @Size(min = 5, max = 120) String adress, @NotNull @Min(4) int age) {
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
        if(name.equals(null)||name.length()<4) {
           throw new IllegalArgumentException("Name must be longer then 3 symbols");
        }
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        if(adress.equals(null)||adress.length()<4) {
            throw new IllegalArgumentException("Name must be longer then 3 symbols");
        }
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<GroupClimbers> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupClimbers> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return '\n'+"Climber{"+" Id of: "+getId()+" "+
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", age=" + age +
                '}';
    }
}
