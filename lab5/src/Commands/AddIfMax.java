package Commands;

import Exceptions.IncorrectInputException;
import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.UserManager;
import data.LabWork;

import java.time.LocalDateTime;

/**
 * Команда 'Add If Max'. Добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.
 */
public class AddIfMax extends Command{
    private CollectionManager collectionManager;
    private Add add;
    private UserManager userManager;

    public AddIfMax(CollectionManager collectionManager,Add add,UserManager userManager){
        super("add_if_max {element}","добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.add = add;
        this.userManager = userManager;
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            if (collectionManager.getCollection().isEmpty()){
                add.execute("");
            } else {
                LabWork labWork = new LabWork(
                        collectionManager.generateNextId(),
                        userManager.askName(),
                        userManager.askCoordinates(),
                        LocalDateTime.now(),
                        userManager.askMinimalPoint(),
                        userManager.askDescription(),
                        userManager.askTunedInWorks(),
                        userManager.askDifficulty(),
                        userManager.askAuthor()
                );
                collectionManager.sort();
                if (labWork.compareTo(collectionManager.getCollection().getLast()) > 0){
                    collectionManager.addToCollection(labWork);
                    System.out.println("Элемент успешно добавлен!");
                } else { System.out.println("Элемент меньше или равен максимального элемента коллекции!"); }
            }
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } catch (IncorrectInputException ignored){}
        return false;
    }
}
