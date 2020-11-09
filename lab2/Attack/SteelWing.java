package Attack;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;


public class SteelWing extends PhysicalMove {
    public SteelWing(){
        super(Type.STEEL, 70.0, 90.0);
    }
    @Override
    protected String describe() {
        return "применяет Steel Wing";
    }

}