package Global.Commands;


import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;
import Global.data.User;

public class RemoveById extends Command{
    private long id;
    public RemoveById() {
        super("remove_by_id id", "удалить элемент по его id");
    }

    public Request execute(String[] arg,User user){
        try{
            Parsers.verify(arg,1);
            id = Long.parseLong(arg[1]);
            return getRequest(user);
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '"+ getName() + "'");
        } catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(id должно быть целым числом)");
        }
        return new Request(null,null);
    }

    public long getId(){
        return id;
    }
}
