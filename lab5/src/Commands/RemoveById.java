package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;
import data.LabWork;

import java.util.NoSuchElementException;

public class RemoveById extends Command{
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент по его id");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if (arg.isEmpty()) throw new WrongArgument();
            long longId = Long.parseLong(arg);
            boolean boo = true;
            for (LabWork labWork: collectionManager.getCollection()){
                if (labWork.getId() == longId){
                    collectionManager.removeById(longId);
                    boo = false;
                }
            }
            if (boo) throw new NoSuchElementException();
            System.out.println("Элемент с id="+longId+" удалён!");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(id должно быть целым числом)");
        } catch (NoSuchElementException e){
            System.out.println("error: В коллекции нет элемента с таким id!");
        }
      return false;
    }
}
