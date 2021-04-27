package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import data.LabWork;
import sun.misc.Queue;

import java.util.*;


public class PrintAscending extends Command{
    private CollectionManager collectionManager;
    private Show show;

    public PrintAscending(CollectionManager collectionManager, Show show){
        super("print_ascending","вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
        this.show = show;
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            collectionManager.sort();
            show.execute("");
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } return false;
    }
}
