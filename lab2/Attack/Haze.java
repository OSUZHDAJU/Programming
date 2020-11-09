package Attack;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;

public class Haze extends StatusMove {
    public Haze() {
        super(Type.ICE,0.0,0.0);
    }
    protected void changeMod(Pokemon pokemon) {
        pokemon.setMod(Stat.HP, -12);
        pokemon.setMod(Stat.HP, 6);
        pokemon.setMod(Stat.ATTACK, -12);
        pokemon.setMod(Stat.ATTACK, 6);
        pokemon.setMod(Stat.DEFENSE, -12);
        pokemon.setMod(Stat.DEFENSE, 6);
        pokemon.setMod(Stat.SPEED, -12);
        pokemon.setMod(Stat.SPEED, 6);
        pokemon.setMod(Stat.SPECIAL_ATTACK, -12);
        pokemon.setMod(Stat.SPECIAL_ATTACK, 6);
        pokemon.setMod(Stat.SPECIAL_DEFENSE, -12);
        pokemon.setMod(Stat.SPECIAL_DEFENSE, 6);
        pokemon.setMod(Stat.ACCURACY, -12);
        pokemon.setMod(Stat.ACCURACY, 6);
        pokemon.setMod(Stat.EVASION, -12);
        pokemon.setMod(Stat.EVASION, 6);
    }
    @Override
    protected String describe() {
        return "применяет Haze";
    }
}

