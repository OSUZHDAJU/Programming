package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import data.LabWork;
import sun.misc.Queue;

import java.util.*;


public class PrintAscending extends Command{
    private CollectionManager collectionManager;

    public PrintAscending(CollectionManager collectionManager){
        super("print_ascending","вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            collectionManager.getCollection().toArray();

        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } return false;
    }
}
