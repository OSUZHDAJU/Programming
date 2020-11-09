package Attack;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;


public class IronHead extends PhysicalMove {
    public IronHead(){
        super(Type.STEEL, 80.0, 100.0);
    }
    @Override
    protected String describe() {
        return "применяет Iron Head";
    }

}