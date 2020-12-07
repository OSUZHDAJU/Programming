
public class Znaika implements MainHeroMovement {
    public static Znaika znaika;
    public String name;
    public String state = "normal";

    public static synchronized Znaika getznaika(String name) {
        if (znaika == null) {
            znaika = new Znaika();
            znaika.name = name;
        }
        return znaika;
    }

    public String getName() {
        return name;
    }

    public static String caseofZnaika(String nameofcase){
        switch (nameofcase) {
            case ("NOMINATIVE"):
                return "он";
            case ("BIGNOMINATIVE"):
                return "Он";
            case ("ACCUSATIVE"):
                return "его";
            case ("GENETIVE"):
                return "его";
            case ("DATIVE"):
                return "нему";
            case ("DATIVENAME"):
                return "Знайке";
        }
        return null;
    }

    @Override
    public void runout(Place place) {
        System.out.print(getName() + " выбежал из " + place.name);
    }
    @Override
    public void rush(Place place) {
        System.out.println(" и во весь дух помчался по " + place.name + ".");
    }
    @Override
    public void runup(Place place, Place secondplace) {
        System.out.print("Через пять минут " + caseofZnaika("NOMINATIVE") + " уже подбежал к " + place.name + ", а ещё через минуту ворвался на " + secondplace.name);
    }
    @Override
    public void stay() {
        System.out.println(" и останавился как вкопанный.");
    }

    public void hope() {
        System.out.println(getName()+" надеялся, что увидит "+Rocket.caseofrocket("ACCUSATIVE")+", лежащую поперёк площади.");
    }

    public void imagine() {
        System.out.print(caseofZnaika("BIGNOMINATIVE")+" представлял себе, как "+Rocket.getShename()+" лежит");
    }

    public void see() {
        System.out.println(", поэтому то, что увидел "+getName()+", привело "+caseofZnaika("ACCUSATIVE")+" в изумление.");
        this.state = "excited";
    }

    public void feeling() {
        if (state == "excited"){
            System.out.print("Чувствуя, что ");
            Legs.stiffened();
        }
    }

    @Override
    public void wade(Place place) {
        System.out.print(getName()+" пробрался к "+place.name+" и ");
    }

    public void inspect() {
        System.out.println("произвёл тщательнейший осмотр.");
    }

    public void standing() {
        System.out.println("Не зная, что думать, "+getName()+" стоял и растерянно озирался по сторонам.");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void come() {
        System.out.println(getName()+" увидел, что к нему бегут "+Girls.GIRL1.getName()+" и "+Girls.GIRL2.getName()+".");
    }

}




