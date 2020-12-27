package tourism;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mountains")
public class Mountain extends PrimeID{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private int height;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mountain")
    private Set<GroupClimbers> clGroups;

    public Mountain() {
    }

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if(country.equals(null)||country.length()<4) {
            throw new IllegalArgumentException("Name must be longer then 3 symbols");
        }
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height<100){
            throw new IllegalArgumentException("The mountain lesser then 100 meters high is called, a hill, and is not valid!");
        }
        this.height = height;
    }
    public Set<GroupClimbers> getClGroups() {
        return clGroups;
    }
    public void setClGroups(Set<GroupClimbers> clGroups) {
        this.clGroups = clGroups;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}
