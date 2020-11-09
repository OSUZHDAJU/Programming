package Pokemon;

import Attack.Haze;
import Attack.RockTomb;
import Attack.Confide;
import Attack.Blizzard;
import ru.ifmo.se.pokemon.Type;

public class Quagsire extends Wooper {
    public Quagsire(String name, int level) {
        super(name,level);
        this.setStats(95,85,85,65,65,35);
        this.setType(Type.WATER,Type.GROUND);
        this.setMove(new Haze(),new Confide(),new Blizzard(),new RockTomb());
    }
}