package Attack;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;

public class Blizzard extends SpecialMove {
    public Blizzard(){
        super(Type.ICE, 110.0, 70.0);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() <= 0.1)
            Effect.freeze(pokemon);
    }
    @Override
    protected String describe() {
        return "применяет Blizzard";
    }

}