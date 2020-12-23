package tourism;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mountains")
public class Mountain extends PrimeID{
    @NotNull
    @Size(min = 4,max = 42)
    private String name;
    @NotNull
    @Size(min = 4,max = 42)
    private String country;
    @NotNull
    @Min(101)
    private int height;

    public Mountain() {
    }

    public Mountain(@NotNull @Size(min = 4, max = 42) String name, @NotNull @Size(min = 4, max = 42) String country, @NotNull @Min(101) int height) {
        this.name = name;
        this.country = country;
        this.height = height;
    }
}
