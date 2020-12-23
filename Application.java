import Dao.ClimberDao;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import tourism.Climber;
import tourism.GroupClimbers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;

public class Application {
    public static void main(String[] args) {


        Climber climber1 = new Climber("climber1","fsdffd",18);
        Climber climber2 = new Climber("climber2","fsdffd",18);
        Climber climber3= new Climber("climber3","fsdffd",111);
        Climber climber4 = new Climber("climber4","fsdffd",122);
        Climber climber5 = new Climber("climber5","fsd234ffd",19);



        EntityManagerFactory factory = Persistence.createEntityManagerFactory("forexam4");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        manager.getTransaction().commit();
        ClimberDao dao =new ClimberDao(manager);
        List<Climber> climberList = dao.getAll();
        System.out.println(dao.getAllprimitive());

        System.out.println("The readed list is "+climberList);



    }
}
