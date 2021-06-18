package Server;


import Global.Commands.*;
import Global.Request;
import Global.Response;
import Global.data.LabWork;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RequestManager {
    private List<String> scriptFiles = new ArrayList<>();
    private ObjectInputStream clientReader;
    private ObjectOutputStream clientSender;
    private CollectionManager collectionManager;

    public RequestManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response process(Request request){
       try{
           Command command = request.getCommand();
           System.out.println("Обрабатываю команду "+ command.getName());
           collectionManager.addToHistory(command.getName());
           return executeRequest(command);
       }catch (NullPointerException e){
           return null;
       }
    }

    public CollectionManager getCollectionManager(){
        return collectionManager;
    }

    private Response executeRequest(Command command){
        try{
            String nowCommand = command.getName();
            switch(nowCommand){
                case "add {element}":
                    LabWork labWork = ((Add) command).getLabWork();
                    return collectionManager.add(labWork);
                case "add_if_max {element}":
                    LabWork labWork1 = ((AddIfMax) command).getLabWork();
                    return collectionManager.addIfMax(labWork1);
                case "clear":
                    return collectionManager.clear();
                case "count_less_than_minimal_point minimalPoint":
                    return collectionManager.countLessMinimalPoint(((CountLessThanMinimalPoint)command).getMinimum());
                case "execute_script file_name":
                    return executeScript(((ExecuteScript)command).getFilename());
                case "head":
                    return collectionManager.head();
                case "help":
                    return collectionManager.help();
                case "history":
                    return collectionManager.history();
                case "info":
                    return collectionManager.info();
                case "min_by_difficulty":
                    return collectionManager.getMinByDifficulty();
                case "print_ascending":
                    return collectionManager.printAscending();
                case "remove_by_id id":
                    return collectionManager.removeById2(((RemoveById)command).getId());
                case "show":
                    return collectionManager.show();
                case "update id {element}":
                    return collectionManager.updateId(((Update)command).getLabWork(),((Update)command).getId());
                default:
                    break;
            }
        } catch (NullPointerException e){
            System.out.println("Получен неверный запрос от клиента.");
        }
        return null;
    }


    private Response executeScript(String fileName){
        String[] nowCommand = {"",""};
        scriptFiles.add(fileName);
        boolean status = true;
        try(Scanner scriptScanner = new Scanner(new File(fileName))){
            if (!scriptScanner.hasNextLine()) throw new NoSuchElementException();
            do{
                nowCommand = (scriptScanner.nextLine().trim()+" ").split(" ",2);
                nowCommand[1] = nowCommand[1].trim();
                System.out.println(nowCommand[0]+" "+nowCommand[1]);
                status = launchCommand(nowCommand);
            } while (scriptScanner.hasNextLine() && status);
            if (!status) return new Response("Вызвана рекурсия скрипта!");
            return new Response("Скрипт выполнен!");
        } catch (FileNotFoundException e){
            return new Response("Файл с именем "+fileName+" отсутствует!");
        } catch (NoSuchElementException e){
            return new Response("Файл "+fileName+" пуст!");
        }
    }

    private boolean launchCommand(String[] command){
        try{
            collectionManager.help(command[0]);
            collectionManager.info(command[0]);
            if (command[0].equals("execute_script")){
                for (String fileName : scriptFiles){
                    if (command[1].equals(fileName)) throw new FileNotFoundException();
                }
                executeScript(command[1]);
            }
            return true;
        } catch (FileNotFoundException e){
            return false;
        }
    }

}
