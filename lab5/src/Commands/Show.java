package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;
import data.LabWork;

public class Show extends Command{
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            System.out.println("Элементы коллекции:");
            int i=0;
            for (LabWork labWork : collectionManager.getCollection()){
                 System.out.println(++i +") "+ labWork.toString());
            }
        return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } return false;
    }
}
