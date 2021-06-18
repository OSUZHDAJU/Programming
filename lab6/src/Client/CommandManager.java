package Client;

import Global.Exceptions.UniqueException;
import Global.Request;
import Global.Commands.*;
import Global.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandManager {
    private String[] commandWithArgument;
    public static boolean mode; // if TRUE then "User Mode", else "File Mode"
    private int historySize = 7;
    private String[] listHistory = new String[historySize];
    private List<Command> commands = new ArrayList<>();
    private Command add;
    private Command addIfMax;
    private Command clear;
    private Command countLessThanMinimalPoint;
    private Command executeScript;
    private Command head;
    private Command help;
    private Command history;
    private Command info;
    private Command minByDifficulty;
    private Command printAscending;
    private Command removeById;
    private Command show;
    private Command update;
    private UserManager userManager;
    private String command;
    private Scanner scanner;

    public CommandManager(Command add, Command addIfMax,Command clear, Command countLessThanMinimalPoint, Command executeScript,
                 Command head, Command help, Command history, Command info, Command minByDifficulty,
                           Command printAscending,Command removeById, Command show, Command update , UserManager userManager) {
        this.add = add;
        commands.add(add);
        this.addIfMax = addIfMax;
        commands.add(addIfMax);
        this.clear = clear;
        commands.add(clear);
        this.countLessThanMinimalPoint = countLessThanMinimalPoint;
        commands.add(countLessThanMinimalPoint);
        this.executeScript = executeScript;
        commands.add(executeScript);
        this.head = head;
        commands.add(head);
        this.help = help;
        commands.add(help);
        this.history = history;
        commands.add(history);
        this.info = info;
        commands.add(info);
        this.minByDifficulty = minByDifficulty;
        commands.add(minByDifficulty);
        this.printAscending = printAscending;
        commands.add(printAscending);
        this.removeById = removeById;
        commands.add(removeById);
        this.show = show;
        commands.add(show);
        this.update = update;
        commands.add(update);
        command = "";

        this.userManager = userManager;
        scanner = userManager.getUserScanner();
    }

    public String[] readCommand(){
        mode = true;
        if (command.equals("exit") || !(scanner.hasNext())){
            System.out.println("Работа клиента завершена.");
            System.exit(0);
        }
        command = scanner.nextLine();
        commandWithArgument = command.trim().split(" ");
        return commandWithArgument;
    }

    public Command getAdd(){
        return add;
    }

    public Request generateRequest(String[] commandWithArgument) {
        try {
            switch (commandWithArgument[0]) {
                case "":
                    break;
                case "add":
                    return add.execute(commandWithArgument);
                case "add_if_max":
                    return addIfMax.execute(commandWithArgument);
                case "clear":
                    return clear.execute(commandWithArgument);
                case "count_less_than_minimal_point":
                    return countLessThanMinimalPoint.execute(commandWithArgument);
                case "exit":
                    break;
                case "head":
                    return head.execute(commandWithArgument);
                case "help":
                    return help.execute(commandWithArgument);
                case "history":
                    return history.execute(commandWithArgument);
                case "info":
                    return info.execute(commandWithArgument);
                case "min_by_difficulty":
                    return minByDifficulty.execute(commandWithArgument);
                case "print_ascending":
                    return printAscending.execute(commandWithArgument);
                case "remove_by_id":
                    return removeById.execute(commandWithArgument);
                case "show":
                    return show.execute(commandWithArgument);
                case "update":
                    return update.execute(commandWithArgument);
                case "execute_script":
                    return executeScript.execute(commandWithArgument);
                default:
                    System.out.println("Нет команды " + commandWithArgument[0] + ". Введите 'help' для вывода списка доступных команд.");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка чтения команды. Введите 'help' для вывода списка доступных команд.");
        }
        return null;
    }

}
