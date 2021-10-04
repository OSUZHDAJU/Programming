package Global.Commands;

import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;
import Global.data.User;

/**
 * Команда 'head'. Выводит первый элемент коллекции.
 */
public class Head extends Command{

    public Head(){
        super("head","вывести первый элемент коллекции");

    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public Request execute(String[] arg, User user){
        try{
            Parsers.verify(arg,0);
            return getRequest(user);
        } catch (InvalidAmountOfArgumentsException e) {
            System.out.println("Использование команды: '" + getName() + "'");
        }
        return null;
    }
}