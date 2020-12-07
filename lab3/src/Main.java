
public class Main {
    public static void main(String[] args) {
       Znaika.getznaika("Знайка");
       Znaika.getznaika("тест");
       Rocket rocket = new Rocket("ракета");
       Square square = new Square("Стартовая площадка");
       Znaika znaika = Znaika.znaika;
       znaika.runout(new Place("калитки"));
       znaika.rush(new Place("улице"));
       znaika.runup(new Place("Космическому городку"), new Place("круглую площадь"));
       znaika.stay();
       znaika.hope();
       znaika.imagine();
       znaika.see();
       rocket.wasnthere(new Place("площади"));
       rocket.getStatutes();
       znaika.feeling();
       znaika.wade(new Place("стартовой площадке"));
       znaika.inspect();
       square.state();
       rocket.canfall();
       znaika.standing();
       znaika.come();
       Girls.GIRL1.excited();
       Girls.GIRL1.want();
       Girls.GIRL1.cantsay();
       //System.out.println(znaika.hashCode()+" "+znaika.toString()+" "+rocket.equals(rocket));
    }
}
