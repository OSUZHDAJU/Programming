package Global.Commands;

import Global.Parsers;
import Global.Request;
import Global.Exceptions.*;
import Global.data.User;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class Clear extends Command{


    public Clear(){
        super("clear","очистить коллекцию");
    }
    public Clear(String name) {super(name,"123");}

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public Request execute(String[] arg, User user) {
        try {
            Parsers.verify(arg, 0);
            return getRequest(user);
        }  catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '" + getName() + "'");
        }
        return new Request(null,null);
    }
}
