package Attack;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;


public class WingAttack extends PhysicalMove {
    public WingAttack(){
        super(Type.FLYING,60.0,100.0);
    }
    @Override
    protected String describe() {
        return "применяет Wing Attack   ";
    }

}