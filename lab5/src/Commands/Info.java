package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;

public class Info extends Command{
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            System.out.println("Информация о коллекции:");
            System.out.println("Тип: "+ collectionManager.getCollectionType());
            System.out.println("Количество элементов: "+ collectionManager.getSize());
            System.out.println("Дата инициализации: "+ collectionManager.getInitializationTime());
            return true;

        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } return false;
    }
}
