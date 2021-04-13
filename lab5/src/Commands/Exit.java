package Commands;

import Exceptions.WrongArgument;

public class Exit extends Command{
    public Exit(){
        super("exit","завершить программу(без сохранения коллекции)");
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            System.out.println("Программа завершена! До свидания;)");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } return false;
    }
}
