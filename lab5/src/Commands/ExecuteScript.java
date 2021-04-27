package Commands;

import Exceptions.WrongArgument;

/**
 * Команда 'execute_script'. Считывает и исполняет скрипт из указаннго файла.
 */
public class ExecuteScript extends Command{

    public ExecuteScript(){
        super("execute_script file_name","считать и исполнить скрипт из указанного файла");
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
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
