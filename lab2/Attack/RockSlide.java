package Attack;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class RockSlide extends PhysicalMove {
    public RockSlide(){
        super(Type.ROCK, 75.0, 90.0);
    }
    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random()< 0.3)
            Effect.flinch(pokemon);
    }
    @Override
    protected String describe() {
        return "применяет Rock Slide";
    }
}