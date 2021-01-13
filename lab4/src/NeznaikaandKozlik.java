import java.util.Objects;

public class NeznaikaandKozlik implements MainHeroMovement {
    public static NeznaikaandKozlik heroes;
    private String firstname;
    private String secondname;

    public static NeznaikaandKozlik getHeroes(){
        return heroes;
    }

    private Seller seller = null;

    public static NeznaikaandKozlik setHeroes(String name1, String name2) {
        if (heroes == null) {
            heroes = new NeznaikaandKozlik();
            heroes.firstname = name1;
            heroes.secondname = name2;
        }
        return heroes;
    }

    public String get1Name() {
        return firstname;
    }

    public String get2Name() {
        return secondname;
    }

    public String getallName() {
        return "Незнайка и козлик";
    }


    public static String caseofHeroes(String nameofcase){
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
                return "Незнайке";
            case("ROD"):
                return "Незнайки и Козлика";
            case("they"):
                return "они";
            case("friends"):
                return "Друзья";
        }
        return null;
    }


    public void nothere() {
        System.out.println(caseofHeroes("ROD") + " след простыл. ");
    }

    @Override
    public void godown(Place place) {
        System.out.print("Спустившись, " + caseofHeroes("they") + " побежали через" + Place.getDestiny(true).checkpoint());
    }

    public void findOneself() {
        System.out.println("Очутившись на другой улице, " + caseofHeroes("they") + " смешались с толпой и вскоре были далеко от места происшествия.");
    }

    @Override
    public void come(Place place, Place secondplace) {
        System.out.println(caseofHeroes("friends")+" быстро прошли на "+ place.getName() +", свернули в "+ secondplace.getName() +" и стали искать "+ Shop.getName() + ", но его нигде не было.");
    }

    public void examine(Place place) {
        System.out.print(caseofHeroes("they")+" обследовали весь "+place.getName()+" от начала и до конца");
    }

    public void examine2(){
        System.out.print(", а потом в обратном порядке");
    }

    public void examine3(){
        System.out.println(", потом прошлись по нему в третий раз.");
    }

    public void imagine(Place place){
        System.out.println("Наконец "+get2Name()+" остановился возле "+place.getName()+", которого, как ему показалось, раньше здесь не было.");
    }

    @Override
    public void comein(Place place){
        System.out.println(getallName()+" вошли в "+place.getName()+" и спросили одну из "+Seller.getRod() +", не знает ли она, куда делся "+Shop.getshortName()+".");
    }

    @Override
    public void read(){
        System.out.println(caseofHeroes("BIGNOMINATIVE")+" вытащил из кармана записку, которую оставил Жулио, и принялся снова ее читать.");
    }

    public void fear(Place place){
        System.out.println(place.getName()+" отходил лишь в конце дня, но "+getallName()+" боялись возвращаться в гостиницу, где они могли попасть в руки невольно обманутых ими акционеров.");
    }

    public void find(Place place){
        System.out.print("Проболтавшись до обеда в городском парке, друзья разыскали "+place.getName()+", где никогда до этого не бывали");
    }

    @Override
    public void lunch(){
        System.out.println(", и как следует пообедали, оставив там почти все свои наличные капиталы.");
    }

    public void buy(){
        System.out.println("Оставшиеся несколько сантиков они истратили на мороженое и купили бутылку газированной воды с сиропом, которую решили взять с собой в дорогу.");
    }

    public String toString(){
        return "Znaika{" + "name='" + firstname + "' , " + "secondname='" + secondname + "'" + '}' ;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NeznaikaandKozlik heroes = (NeznaikaandKozlik) obj;
        return Objects.equals(firstname, heroes.firstname);

    }


    public int hashCode() {
        return Objects.hash(firstname);
    }



}



