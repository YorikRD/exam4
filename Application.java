import Dao.ClimberDao;
import Dao.GroupClimberDao;
import Dao.MountainDao;
import specification.ClimberInAgeOf;
import specification.GroupAvailable;
import specification.GroupByMountainName;
import specification.MountainByCountry;
import staticGen.StaticStrings;
import tourism.Climber;
import tourism.GroupClimbers;
import tourism.Mountain;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {

    static ClimberDao climberDao = new ClimberDao();
    static  GroupClimberDao groupClimberDao = new GroupClimberDao();
    static  MountainDao mountainDao = new MountainDao();

    public static void main(String[] args) {
//        List<Mountain> mountainsForCheck = genMountains();
////        System.out.println(mountainsForCheck);
//        for (Mountain mountain : mountainsForCheck) {
//            mountainDao.add(mountain);
//        }
//        List<GroupClimbers> groupClimbersForCheck = genGroups(mountainsForCheck);
//        for (GroupClimbers groupClimbers : groupClimbersForCheck) {
//            groupClimberDao.add(groupClimbers);
//        }
////        System.out.println(groupClimbersForCheck);
//        List<Climber> climbersForCheck = genClimbers(groupClimbersForCheck);
//        for (Climber climber : climbersForCheck) {
//            climberDao.add(climber);
//        }
////        System.out.println(climbersForCheck);

        checkExamMethods();





    }
    public static List<Climber> genClimbers(List<GroupClimbers> groups){
        List<Climber> climbers = new ArrayList<>();
        int nomb = groups.size()*6;
        for (int i = 0; i < nomb; i++) {
            int randN = (int)(Math.random()*StaticStrings.names.length);
            String name = StaticStrings.names[randN];
            randN =(int)(Math.random()*StaticStrings.adress.length);
            String adress = StaticStrings.adress[randN];
            Climber climber = new Climber(name,adress,randN+18+(int)(Math.random()*12));
            int randGroupNomb = (int)(Math.random()*3+1);
            for (int j = 0; j < randGroupNomb; j++) {
                int randGr = (int)(Math.random()*groups.size());
                if (groups.get(randGr).isAvailableToJoin()&&!climber.getGroups().contains(groups.get(randGr))){
                    climber.getGroups().add(groups.get(randGr));
                }
            }
            climbers.add(climber);
        }
        return climbers;
    }





    public static List<GroupClimbers> genGroups ( List<Mountain> mountains){
        ArrayList<Mountain>innerM=(ArrayList<Mountain>) mountains;
        List<GroupClimbers> climbers = new ArrayList<>();
        int nomb = mountains.size()*3;
        for (int i = 0; i < nomb; i++) {
            int rand =((int)(Math.random()*mountains.size()));
            boolean available = (rand>=mountains.size()/2);
            GroupClimbers climberGroup = new GroupClimbers(innerM.get(rand),
                    available,LocalDate.now().plusDays(((int)(Math.random()*230))),
                    ((int)(Math.random()*23)+1));
            climbers.add(climberGroup);
        }
        return climbers;
    }

    public static List<Mountain> genMountains (){
        Mountain mountain = new Mountain("Everest","Austrian Empire",1267);
        Mountain mountain2 = new Mountain("Peak of Mars","Bulgaria",12269);
        Mountain mountain3 = new Mountain("Vesuvius","Bulgaria",2269);
        Mountain mountain4 = new Mountain("Kilimanjaro","Russia",2269);
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(mountain);
        mountains.add(mountain2);
        mountains.add(mountain3);
        mountains.add(mountain4);
        return mountains;
    }
    public static GroupClimbers groupClimber (Mountain mountain){
        MountainDao mountainDao = new MountainDao();
        GroupClimbers group1 = new GroupClimbers(mountain,
                true, LocalDate.now().plusDays(126),12);
        return group1;


    }

    public static void checkExamMethods(){

        List<Climber> fromTwoByAge = climberDao.getBySpecification(new ClimberInAgeOf(19,116));
        System.out.println("fromTwoByAge:"+fromTwoByAge);
        List<Mountain> mountainsCheckByCountry = mountainDao.getBySpecification(new MountainByCountry("Bulgaria"));
        System.out.println("mountainsCheckByCountry: "+mountainsCheckByCountry);
        List<GroupClimbers> climbersGrListByMountain = groupClimberDao.getBySpecification(new GroupByMountainName("Everest"));
        System.out.println("climbersGrListByMountain: "+ climbersGrListByMountain);
        List<GroupClimbers> areAvailableToJoin = groupClimberDao.getBySpecification(new GroupAvailable(true));
        System.out.println("areAvailableToJoin: "+areAvailableToJoin);
    }

}
