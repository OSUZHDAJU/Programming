package Attack;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;


public class RockTomb extends PhysicalMove {
    public RockTomb(){
        super(Type.ROCK, 60.0, 95.0);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPEED, -1);
    }
    @Override
    protected String describe() {
        return "применяет Rock Tomb";
    }
}