package Global.Commands;

import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;


public class Help extends Command{

    public Help() {
        super("help", "вывести справку по доступным командам");
    }

    public Request execute(String[] arg) {
        try {
            Parsers.verify(arg,0);
            return getRequest();
        } catch (InvalidAmountOfArgumentsException e) {
            System.out.println("Использование команды: '" + getName() + "'");
        }
        return null;
    }
}
