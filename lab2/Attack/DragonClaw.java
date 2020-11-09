package Attack;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;


public class DragonClaw extends PhysicalMove {
    public DragonClaw(){
        super(Type.DRAGON, 80.0, 100.0);
    }
    @Override
    protected String describe() {
        return "применяет Dragon Claw";
    }

}