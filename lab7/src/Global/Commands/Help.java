package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;
import Global.data.User;


public class Help extends Command{

    public Help() {
        super("help", "вывести справку по доступным командам");
    }

    public Request execute(String[] arg, User user) {
        try {
            Parsers.verify(arg,0);
            return getRequest(user);
        } catch (InvalidAmountOfArgumentsException e) {
            System.out.println("Использование команды: '" + getName() + "'");
        }
        return null;
    }
}
