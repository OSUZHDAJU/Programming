import java.util.Objects;

public class Neznaika {
    private static Neznaika neznaika;
    private String name;

    private Neznaika(String name1) {
        this.name = name1;
    }

    public static Neznaika getHeroes(){
        return neznaika;
    }

    MainHeroMovement movement = new MainHeroMovement() {
        @Override
        public void godown(Place place) {
            System.out.println("Спустившись, " + caseofHeroes("they") + " побежали через" + Place.getDestiny(true).checkpoint());
        }

        @Override
        public void come(Place place, Place secondplace) {
            System.out.println(caseofHeroes("friends") + " быстро прошли на " + place.getName() + ", свернули в " + secondplace.getName() + " и стали искать " + Shop.getName() + ", но его нигде не было.");
        }

        @Override
        public void comein(Place place) {
            System.out.println(get1Name() +" и "+Kozlik.getKozlik().getName()+ " вошли в " + place.getName() + " и спросили одну из " + Seller.getRod() + ", не знает ли она, куда делся " + Shop.getshortName() + ".");
        }

        @Override
        public void read() {
            System.out.println(caseofHeroes("BIGNOMINATIVE") + " вытащил из кармана записку, которую оставил Жулио, и принялся снова ее читать.");
            class Note{

                final boolean status = true;

                public void check(){
                    if (!status){
                        System.out.println("Записки в кармане не оказалось.");
                    }
                }
            }
        }
    };

    public static Neznaika setNeznaika(String name1) {
        if (neznaika == null) {
            neznaika = new Neznaika(name1);
        }
        return neznaika;
    }

    public String get1Name() {
        return name;
    }

    public static String caseofHeroes(String nameofcase) {
        switch (nameofcase) {
            case ("NOMINATIVE"):
                return "он";
            case ("BIGNOMINATIVE"):
                return "Он";
            case ("ACCUSATIVE"):
            case ("GENETIVE"):
                return "его";
            case ("DATIVE"):
                return "нему";
            case ("DATIVENAME"):
                return "Незнайке";
            case ("ROD"):
                return "Незнайки и Козлика";
            case ("they"):
                return "они";
            case ("They"):
                return "Они";
            case ("friends"):
                return "Друзья";
        }
        return null;
    }



    public void nothere() {
        System.out.println(
                caseofHeroes("ROD") +" след простыл. ");
        }


    public void findOneself() {
        System.out.println("Очутившись на другой улице, " + caseofHeroes("they") + " смешались с толпой и вскоре были далеко от места происшествия.");
    }

    public void examine(Place place,int count) {
        switch (count){
            case(2):
                System.out.print(caseofHeroes("They")+" обследовали весь "+place.getName()+" от начала и до конца");
                System.out.println(", а потом в обратном порядке.");
                break;
            case(3):
                System.out.print(caseofHeroes("They")+" обследовали весь "+place.getName()+" от начала и до конца");
                System.out.print(", а потом в обратном порядке");
                System.out.println(", потом прошлись по нему в третий раз.");
                break;
            default:
                System.out.println(caseofHeroes("They")+" обследовали весь "+place.getName()+" от начала и до конца.");
                break;
        }
    }

    public void fear(Place place){
        System.out.println(place.getName()+" отходил лишь в конце дня, но "+get1Name() +" и "+Kozlik.getKozlik().getName()+" боялись возвращаться в гостиницу, где они могли попасть в руки невольно обманутых ими акционеров.");
    }

    public void find(Place place){
        System.out.print("Проболтавшись до обеда в городском парке, друзья разыскали "+place.getName()+", где никогда до этого не бывали");
    }

    public void lunch(){
        System.out.println(", и как следует пообедали, оставив там почти все свои наличные капиталы.");
    }

    public void buy(){
        System.out.println("Оставшиеся несколько сантиков "+caseofHeroes("they") +" истратили на мороженое и купили бутылку газированной воды с сиропом, которую решили взять с собой в дорогу.");
    }

    public String toString(){
        return "Neznaika{" + "name='" + name + '}' ;
    }

    public int hashCode() {
        return Objects.hash(name);
    }



}




