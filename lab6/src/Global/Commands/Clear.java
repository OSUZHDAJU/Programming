package Global.Commands;

import Global.Parsers;
import Global.Request;
import Global.Exceptions.*;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class Clear extends Command{


    public Clear(){
        super("clear","очистить коллекцию");
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public Request execute(String[] arg) {
        try {
            Parsers.verify(arg, 0);
            return getRequest();
        }  catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '" + getName() + "'");
        }
        return null;
    }
}
