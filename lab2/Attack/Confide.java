package Attack;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;

public class Confide extends StatusMove {
    public Confide() {
        super(Type.NORMAL,0.0,0.0);
    }
    protected void changeMod(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, -1);
    }
    @Override
    protected String describe() { return "применяет Confide"; }
}
