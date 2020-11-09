package Attack;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Boomburst extends SpecialMove {
    public Boomburst(){
        super(Type.NORMAL, 140.0, 100.0);
    }
    @Override
    protected String describe() {
        return "применяет Boomburst";
    }

}