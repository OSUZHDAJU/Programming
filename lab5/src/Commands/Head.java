package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;
import data.LabWork;

public class Head extends Command{
    private CollectionManager collectionManager;

    public Head(CollectionManager collectionManager){
        super("head","вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            LabWork labWork = collectionManager.firstElement();
            if (labWork == null) throw new NullPointerException();
            else System.out.println(labWork);
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } catch (NullPointerException e){
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
