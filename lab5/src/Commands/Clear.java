package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class Clear extends Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        super("clear","очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new WrongArgument();
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена!");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } return false;
    }
}
