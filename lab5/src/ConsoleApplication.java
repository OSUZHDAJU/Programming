import Managers.CollectionManager;
import Managers.CommandsManager;
import Managers.FileManager;
import Commands.*;
import Managers.UserManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Главный класс приложения. Создаёт экземпляры команд и заупскает приложение.
 * @author Васильков Никита
 */


public class ConsoleApplication {
    public static void main(String[] args) {
        Scanner userScanner = new Scanner(System.in);
        final String fileName = "LabaXML.txt";
        int status;
        String[] nowCommand = {"",""};

        UserManager userManager = new UserManager(userScanner);
        FileManager fileManager = new FileManager(fileName);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        Add add = new Add(collectionManager, userManager);
        AddIfMax addIfMax = new AddIfMax(collectionManager,add,userManager);
        Clear clear = new Clear(collectionManager);
        CountLessThanMinimalPoint countLessThanMinimalPoint = new CountLessThanMinimalPoint(collectionManager);
        ExecuteScript executeScript = new ExecuteScript();
        Exit exit = new Exit();
        Head head = new Head(collectionManager);
        Help help = new Help();
        History history = new History();
        Info info = new Info(collectionManager);
        MinByDifficulty minByDifficulty = new MinByDifficulty(collectionManager);
        RemoveById removeById = new RemoveById(collectionManager);
        Save save = new Save(fileManager,collectionManager);
        Show show = new Show(collectionManager);
        PrintAscending printAscending = new PrintAscending(collectionManager, show);
        Update update = new Update(collectionManager, userManager);
        CommandsManager commandsManager = new CommandsManager(add,addIfMax,clear,countLessThanMinimalPoint,executeScript,exit,head,help,history,info,minByDifficulty,printAscending,removeById,save,show,update,userManager);
        System.out.println("Добро пожаловать! Для показа справки по всем доступным командам введите 'help'. ");

        try{
            do{
                nowCommand = (userScanner.nextLine().trim()+ " ").split(" ",2);
                nowCommand[1] = nowCommand[1].trim();
                commandsManager.addToHistory(nowCommand[0]);
                status = commandsManager.launchCommand(nowCommand);
            }while (status != 2);
        } catch (NoSuchElementException e){
            System.out.println("Ввод не обнаружен!");
        }
        //System.out.println(collectionManager.getCollection());
    }

}
