package Global.Commands;

import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Exceptions.UniqueException;
import Global.Parsers;
import Global.Request;
import Global.data.User;


/**
 * Команда 'count_less_than_minimal_point'. Выводит количество элементов, значение поля minimalPoint которых меньше заданного.
 */
public class CountLessThanMinimalPoint extends Command{
    private Long minimum;
    public CountLessThanMinimalPoint(){
        super("count_less_than_minimal_point minimalPoint","вывести количество элементов, значение поля minimalPoint которых меньше заданного");
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public Request execute(String[] arg, User user) {
        try{
            Parsers.verify(arg, 1);
            Parsers.parseMinimalPoint(arg[1]);
            minimum = Long.parseLong(arg[1]);
            return getRequest(user);
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '" + getName() + "'");
        } catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(minimalPoint должно быть целым числом)");
        } catch (UniqueException e){}
        return new Request(null,null);
    }

    public Long getMinimum(){
        return minimum;
    }
}
