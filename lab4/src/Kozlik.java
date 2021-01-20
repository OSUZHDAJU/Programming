import java.util.Objects;

public class Kozlik {
    private static Kozlik kozlik;
    private String name;

    private Kozlik(String name){
        this.name = name;
    }

    static Kozlik getKozlik() {
        return kozlik;
    }

    public String getName() {
        return name;
    }

    public static Kozlik setKozlik(String name) {
        if (kozlik == null) {
            kozlik = new Kozlik(name);
        }
        return kozlik;
    }
    public void imagine(Place place){
        System.out.println("Наконец "+Kozlik.getKozlik().getName()+" остановился возле "+place.getName()+", которого, как ему показалось, раньше здесь не было.");
    }
    public String toString(){
        return "Kozlik{" + "name='" + name + '}' ;
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}

