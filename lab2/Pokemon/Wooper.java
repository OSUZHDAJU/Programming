package Pokemon;

import Attack.Blizzard;
import Attack.Confide;
import Attack.Haze;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Wooper extends Pokemon {
    public Wooper(String name, int level) {
        super(name,level);
        this.setStats(55,45,45,25,25,15);
        this.setType(Type.WATER,Type.GROUND);
        this.setMove(new Haze(),new Confide(),new Blizzard());
    }
}