package Pokemon;


import Attack.Boomburst;
import Attack.Confide;
import Attack.DragonClaw;
import Attack.RockSlide;
import ru.ifmo.se.pokemon.Type;

class Flygon extends Vibrara {
    public Flygon(String name, int level) {
        super(name,level);
        this.setStats(80,100,80,80,80,100);
        this.setType(Type.DRAGON,Type.GROUND);
        this.setMove(new RockSlide(),new Confide(),new Boomburst(),new DragonClaw());
    }
}