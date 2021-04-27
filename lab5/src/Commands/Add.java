package Commands;

import Exceptions.IncorrectInputException;
import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;
import Managers.UserManager;
import data.LabWork;

import java.time.LocalDateTime;

/**
 * Команда 'add'. Добавляет новый элемнт в коллекцию.
 */
public class Add extends Command{
    private CollectionManager collectionManager;
    private UserManager userManager;

    public Add(CollectionManager collectionManager, UserManager userManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.userManager = userManager;
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public boolean execute (String arg){
        try{
            if(!arg.isEmpty()) throw new WrongArgument();
            collectionManager.addToCollection(new LabWork(
                    collectionManager.generateNextId(),
                    userManager.askName(),
                    userManager.askCoordinates(),
                    LocalDateTime.now(),
                    userManager.askMinimalPoint(),
                    userManager.askDescription(),
                    userManager.askTunedInWorks(),
                    userManager.askDifficulty(),
                    userManager.askAuthor()
            ));
            System.out.println("Элемент успешно добавлен!");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } catch (IncorrectInputException e){}
        return false;
    }
}
