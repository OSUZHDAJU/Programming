package Commands;

import Exceptions.WrongArgument;
import Managers.CommandsManager;

public class History extends Command{

    public History(){
        super("history","вывести последние 7 команд");
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            return true;
        } catch (WrongArgument e) {
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } return false;
    }
}

