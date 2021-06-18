package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;



public class PrintAscending extends Command{

    public PrintAscending(){
        super("print_ascending","вывести элементы коллекции в порядке возрастания");

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
