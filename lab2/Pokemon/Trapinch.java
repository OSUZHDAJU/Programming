package Pokemon;

import Attack.Confide;
import Attack.RockSlide;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Trapinch extends Pokemon {
    public Trapinch(String name,int level){
        super(name,level);
        this.setStats(45,100,45,45,45,10);
        this.setType(Type.GROUND);
        this.setMove(new RockSlide(),new Confide());
    }
}
