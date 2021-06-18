package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;


public class Info extends Command{

    public Info() {
        super("info", "вывести информацию о коллекции");
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
