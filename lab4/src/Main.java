
public class Main {
    public static void main(String[] args) {
       Seller seller = new Seller("Продавщица");
       NeznaikaandKozlik.setHeroes("Незнайка","Козлик");
       Shop shop = new Shop("магазин разнокалиберных товаров");
       NeznaikaandKozlik heroes = NeznaikaandKozlik.getHeroes();
       try{
          if(NeznaikaandKozlik.heroes.get1Name() == null || NeznaikaandKozlik.heroes.get2Name() == null)
             throw new WrongInputExeption("Имя одного из персонажей пусто.");
       }
       catch (WrongInputExeption ex){
          ex.printStackTrace();
       }

       heroes.nothere();
       heroes.godown(new Place("веревке"));
       heroes.findOneself();
       heroes.come(new Place("Крученую улицу"),new Place("Змеиный переулок"));
       heroes.examine(new Place("Змеиный переулок"));
       heroes.examine2();
       heroes.examine3();
       heroes.imagine(new Place("кондитерского магазина"));
       heroes.comein(new Place("кондитерскую"));

       try{
         seller.say();
       }
       catch (SellerExeption ex){
         ex.printStackTrace();
       }

       heroes.read();
       heroes.fear(new Place("Поезд на Сан-Комарик"));
       heroes.find(new Place("небольшую столовую"));
       heroes.lunch();
       heroes.buy();


    }
}
