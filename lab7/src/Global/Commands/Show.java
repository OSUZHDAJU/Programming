package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;
import Global.data.User;


public class Show extends Command{

    public Show() {
        super("show", "вывести все элементы коллекции");
    }

    public Request execute(String[] arg, User user){
        try{
            Parsers.verify(arg,0);
            return getRequest(user);
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '"+ getName() + "'");
        } return new Request(null,null);
    }
}
