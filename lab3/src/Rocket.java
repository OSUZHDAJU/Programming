
public class Rocket extends MySubject implements RocketPosition {
    private static String shename = "она";

    public Rocket(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    public static String getShename() {
        return shename;
    }

    @Override
    public void wasnthere(Place place) {
        System.out.print("На "+place.name+" никакой "+caseofrocket("GENETIVE")+" не было,");
    }

    public static String caseofrocket(String nameofcase){
        switch (nameofcase) {
            case ("NOMINATIVE"):
                return "ракета";
            case ("ACCUSATIVE"):
                return "ракету";
            case ("GENETIVE"):
                return "ракеты";
        }
        return null;
    }

    @Override
    public void canfall() {
        System.out.println("На земле не было ни одной дырочки, в которую могла провалиться "+getName()+".");
    }
    public void getStatutes(){
        System.out.println(Status.NOTSTAY.getStatus() + Status.NOTLIE.getStatus() + Status.NOTWHOLE.getStatus() + Status.NOTBRAKE.getStatus());
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
