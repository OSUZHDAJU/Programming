package Pokemon;

import Attack.IronHead;
import Attack.RockPolish;
import Attack.SteelWing;
import Attack.WingAttack;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Aerodactyl extends Pokemon {
    public Aerodactyl(String name, int level) {
        super(name,level);
        this.setStats(80,105,65,60,75,130);
        this.setType(Type.FLYING,Type.ROCK);
        this.setMove(new IronHead(),new SteelWing(),new WingAttack(),new RockPolish());
    }
}
