package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;
import data.LabWork;

public class MinByDifficulty extends Command{
    private CollectionManager collectionManager;

    public MinByDifficulty(CollectionManager collectionManager){
        super("min_by_difficulty","вывести элемент с минимальным значением difficulty");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if(!arg.isEmpty()) throw new WrongArgument();
            LabWork labWork = collectionManager.getMinByDifficulty();
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
