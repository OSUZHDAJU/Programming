package Global.Commands;

import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;

/**
 * Команда 'execute_script'. Считывает и исполняет скрипт из указаннго файла.
 */
public class ExecuteScript extends Command{

    private String filename;

    public ExecuteScript(){
        super("execute_script file_name","считать и исполнить скрипт из указанного файла");
    }

    /**
     * Выполняет команду.
     * @param arg - аргументы пользователя
     * @return Статус команды.
     */
    public Request execute(String[] arg){
        try{
            Parsers.verify(arg,1);
            filename = arg[1];
            return getRequest();
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '"+ getName() + "'");
        } return null;
    }

    public String getFilename() {
        return filename;
    }
}
