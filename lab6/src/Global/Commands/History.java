package Global.Commands;

import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;

public class History extends Command{

    public History(){
        super("history","вывести последние 7 команд");
    }

    public Request execute(String[] arg){
        try{
            Parsers.verify(arg,0);
            return getRequest();
        } catch (InvalidAmountOfArgumentsException e) {
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } return null;
    }
}

