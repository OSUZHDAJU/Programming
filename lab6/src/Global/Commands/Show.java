package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;


public class Show extends Command{

    public Show() {
        super("show", "вывести все элементы коллекции");
    }

    public Request execute(String[] arg){
        try{
            Parsers.verify(arg,0);
            return getRequest();
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '"+ getName() + "'");
        } return null;
    }
}
