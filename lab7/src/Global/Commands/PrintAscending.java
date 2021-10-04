package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;
import Global.data.User;



public class PrintAscending extends Command{

    public PrintAscending(){
        super("print_ascending","вывести элементы коллекции в порядке возрастания");

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
