package Commands;

import Exceptions.WrongArgument;

public class ExecuteScript extends Command{

    public ExecuteScript(){
        super("execute_script file_name","считать и исполнить скрипт из указанного файла");
    }

    public boolean execute(String arg){
        try{
            if (arg.isEmpty()) throw new WrongArgument();
            System.out.println("Исполняю скрипт из файла '"+ arg +"'");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } return false;

    }
}
