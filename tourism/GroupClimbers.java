package tourism;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupClimbers extends PrimeID{
    @NotNull
    @ManyToOne
    private Mountain mountain;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Climber> members;
    private boolean availableToJoin;
    private LocalDate start;





}
