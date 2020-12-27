import Dao.ClimberDao;
import Dao.GroupClimberDao;
import Dao.MountainDao;
import auxilary.SolitaryManager;
import specification.ClimberInAgeOf;
import staticGen.StaticStrings;
import tourism.Climber;
import tourism.GroupClimbers;
import tourism.Mountain;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ClimberDao climberDao = new ClimberDao();
        GroupClimberDao groupClimberDao = new GroupClimberDao();
        MountainDao mountainDao = new MountainDao();

        Mountain mountain = new Mountain("Everest","Austrian Empire",1267);
        Mountain mountain2 = new Mountain("Peak of Mars","Bulgaria",12269);













    }
    public static void climberAdder (EntityManager manager, List<Climber> climbers){
        manager.getTransaction().begin();
        for (Climber climber : climbers) {
            manager.persist(climber);
        }
        manager.getTransaction().commit();
    }

    public static List<GroupClimbers> genGrouo (int size, List<Mountain> mountains){
        List<GroupClimbers> climbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            GroupClimbers group = new GroupClimbers();
            group.setMountain(mountains.get((int)(Math.random()*mountains.size())));
        }
        return climbers;
    }
    public static List<Mountain> genMountains (int size){
        List<Mountain> mountains = new ArrayList<>();
        Mountain mountain = new Mountain();
        for (int i = 0; i < size; i++) {
            mountain.setName(StaticStrings.mNames[(int)(Math.random()*StaticStrings.mNames.length)]);
            mountain.setCountry(StaticStrings.getCountry);
            mountain.setHeight(StaticStrings.getRHeight);
            mountains.add(mountain);
        }
        System.out.println(mountains);
        return mountains;

    }

}
