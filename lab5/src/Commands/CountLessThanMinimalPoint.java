package Commands;

import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;

/**
 * Команда 'count_less_than_minimal_point'. Выводит количество элементов, значение поля minimalPoint которых меньше заданного.
 */
public class CountLessThanMinimalPoint extends Command{
    private CollectionManager collectionManager;

    public CountLessThanMinimalPoint(CollectionManager collectionManager){
        super("count_less_than_minimal_point minimalPoint","вывести количество элементов, значение поля minimalPoint которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public boolean execute(String arg){
        try{
            if(arg.isEmpty()) throw new WrongArgument();
            long longId = Long.parseLong(arg);
            System.out.println("Количество элементов, значение поля minimalPoint которых меньше "+longId+" = "+collectionManager.countLessThanMinimalPoint(longId)+"/"+collectionManager.getSize()+".");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(minimalPoint должно быть целым числом)");
        } return false;
    }
}
