package tourism;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class PrimeID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Means is generated upon persisting to transaction
    private int id;


    public int getId() {
        return id;
    }


}
