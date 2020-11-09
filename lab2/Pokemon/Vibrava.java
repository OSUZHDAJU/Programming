package Pokemon;


import Attack.Boomburst;
import Attack.Confide;
import Attack.RockSlide;
import ru.ifmo.se.pokemon.Type;

class Vibrara extends Trapinch {
    public Vibrara(String name, int level) {
        super(name,level);
        this.setStats(50,70,50,50,50,70);
        this.setType(Type.DRAGON,Type.GROUND);
        this.setMove(new RockSlide(),new Confide(),new Boomburst());
    }
}