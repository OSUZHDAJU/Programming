package Client;

import Global.Exceptions.IncorrectInputException;
import Global.Request;
import Global.Commands.*;
import Global.UserManager;
import Global.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandManager {
    private String[] commandWithArgument;
    private List<Command> commands = new ArrayList<>();
    private Command add;
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

    public CommandManager(Command add,Command clear, Command countLessThanMinimalPoint, Command executeScript, Command head,
                  Command help, Command history, Command info, Command minByDifficulty,
                           Command printAscending,Command removeById, Command show, Command update , UserManager userManager) {
        this.add = add;
        commands.add(add);
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
        if (command.equals("exit") || !(scanner.hasNext())){
            System.out.println("Работа клиента завершена.");
            System.exit(0);
        }
        command = scanner.nextLine();
        commandWithArgument = command.trim().split(" ");
        return commandWithArgument;
    }

    public Request generateRequest(String[] commandWithArgument , User user) {
        try {
            switch (commandWithArgument[0]) {
                case "":
                case "exit":
                    break;
                case "add":
                    return add.execute(commandWithArgument, user);
                case "clear":
                    return clear.execute(commandWithArgument, user);
                case "count_less_than_minimal_point":
                    return countLessThanMinimalPoint.execute(commandWithArgument, user);
                case "head":
                    return head.execute(commandWithArgument, user);
                case "help":
                    return help.execute(commandWithArgument, user);
                case "history":
                    return history.execute(commandWithArgument, user);
                case "info":
                    return info.execute(commandWithArgument, user);
                case "min_by_difficulty":
                    return minByDifficulty.execute(commandWithArgument, user);
                case "print_ascending":
                    return printAscending.execute(commandWithArgument, user);
                case "remove_by_id":
                    return removeById.execute(commandWithArgument, user);
                case "show":
                    return show.execute(commandWithArgument, user);
                case "update":
                    return update.execute(commandWithArgument, user);
                case "execute_script":
                    return executeScript.execute(commandWithArgument, user);
                default:
                    System.out.println("Нет команды " + commandWithArgument[0] + ". Введите 'help' для вывода списка доступных команд.");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException | IncorrectInputException e){
            System.out.println("Ошибка чтения команды. Введите 'help' для вывода списка доступных команд.");
        }
        return new Request(null,null);
    }

}
