package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;

import java.util.NoSuchElementException;

public class RemoveById extends Command{
    private long id;
    public RemoveById() {
        super("remove_by_id id", "удалить элемент по его id");
    }

    public Request execute(String[] arg){
        try{
            Parsers.verify(arg,1);
            id = Long.parseLong(arg[1]);
            return getRequest();
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '"+ getName() + "'");
        } catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(id должно быть целым числом)");
        }
      return null;
    }

    public long getId(){
        return id;
    }
}
