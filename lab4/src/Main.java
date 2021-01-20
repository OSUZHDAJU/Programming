public class Main {
    public static void main(String[] args) {
       Seller seller = new Seller("Продавщица");
       Shop shop = new Shop("магазин разнокалиберных товаров");
       Neznaika neznaika = Neznaika.setNeznaika("Незнайка");
       Kozlik kozlik = Kozlik.setKozlik("Козлик");

       try{
          if(Neznaika.getHeroes().get1Name() == null || Kozlik.getKozlik().getName() == null)
             throw new WrongInputExeption("Имя одного из персонажей пусто.");
       }
       catch (WrongInputExeption ex){
          ex.printStackTrace();
       }

       neznaika.nothere();
       neznaika.movement.godown(new Place("веревке"));
       neznaika.findOneself();
       neznaika.movement.come(new Place("Крученую улицу"),new Place("Змеиный переулок"));
       neznaika.examine(new Place("Змеиный переулок"),3);
       kozlik.imagine(new Place("кондитерского магазина"));
       neznaika.movement.comein(new Place("кондитерскую"));

       try{
         seller.say();
       }
       catch (SellerExeption ex){
         ex.printStackTrace();
       }

       neznaika.movement.read();
       neznaika.fear(new Place("Поезд на Сан-Комарик"));
       neznaika.find(new Place("небольшую столовую"));
       neznaika.lunch();
       neznaika.buy();
    }
}
