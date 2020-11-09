package Attack;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;

public class RockPolish extends StatusMove {
    public RockPolish() {
        super(Type.ROCK,0.0,0.0);
    }
    protected void changeMod(Pokemon pokemon) {
        pokemon.setMod(Stat.SPEED, 2);
    }
    @Override
    protected String describe() { return "применяет Rock Polish"; }
}

