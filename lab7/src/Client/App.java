package Client;

import Global.LoginManager;
import Global.UserManager;

import Global.Commands.*;

import java.util.Scanner;

public class App {
    public static Scanner userScanner = new Scanner(System.in);
    public static void main(String[] args) {
        UserManager userManager = new UserManager(userScanner);
        Add add = new Add(userManager);
        Clear clear = new Clear();
        CountLessThanMinimalPoint countLessThanMinimalPoint = new CountLessThanMinimalPoint();
        ExecuteScript executeScript = new ExecuteScript();
        Head head = new Head();
        Help help = new Help();
        History history = new History();
        Info info = new Info();
        MinByDifficulty minByDifficulty = new MinByDifficulty();
        RemoveById removeById = new RemoveById();
        Show show = new Show();
        PrintAscending printAscending = new PrintAscending();
        Update update = new Update(userManager);
        LoginManager loginManager = new LoginManager(userManager);
        CommandManager commandManager = new CommandManager(add,clear,countLessThanMinimalPoint,executeScript,head,help,history,info,minByDifficulty,printAscending,removeById,show,update,userManager);
        Client client = new Client("localhost", 3879, loginManager, commandManager);
        client.run();
        userScanner.close();
    }
}
