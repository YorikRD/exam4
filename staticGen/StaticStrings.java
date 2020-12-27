package staticGen;

 public class StaticStrings {
    public  static String[] names ={"Petr","Julia","Pavel","Michael","John","Syao","Olga","Marta"};
    public  static String[] adress ={"3427 Hamilton Ave","1903 N Mill St","214 44th St W","250 Autumn Way", "319 N I St"};
    public  static String[] countries ={"Austrian Empire","Mongolia","Bulgaria","Qatar", "Russia"};
    public  static String[] mNames ={"Everest","Elbrus","Peak of Mars","Vesuvius", "Cilimonjaro"};

    public static String getName =names[(int)((Math.random()*names.length))];
    public static String getAdress =adress[(int)((Math.random()*adress.length))];
    public static String getCountry =countries[(int)((Math.random()*countries.length))];
    public static String getmNames =mNames[(int)((Math.random()*mNames.length))];

    public static int getRAge =(int)(Math.random()*60)+18;
    public static int getRHeight =(int)(Math.random()*200)+100;



}
