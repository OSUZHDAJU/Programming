import java.lang.invoke.SwitchPoint;

public enum Girls {
    GIRL1("Фуксия"),
    GIRL2("Селёдочка");

    public String name;

    Girls(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getgirls(String str){
        switch(str){
            case ("NOMINATIVE"):
                return "Обе";
            case ("ACCUSATIVE"):
                return "обеих";
            case ("THEY"):
                return "они";
        }
        return null;
    }

    public void excited(){
        System.out.println(getgirls("NOMINATIVE")+" были взволнованы.");
        System.out.println("Глаза у "+getgirls("ACCUSATIVE")+" были широко раскрыты.");
    }

    public void want(){
        System.out.print("Подвежав к "+Znaika.znaika.caseofZnaika("DATIVENAME")+", "+getgirls("THEY")+" хотели о чём-то спросить, ");
    }
    public void cantsay(){
        System.out.println("но только беспомощно разевали рты, так как ничего не могли сказать.");
    }
}
