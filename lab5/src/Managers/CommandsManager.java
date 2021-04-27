package Managers;

import Commands.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс отвечающий за работу с командами.
 */
public class CommandsManager {
    private int historySize = 7;
    private String[] listHistory = new String[historySize];
    private List<Command> commands = new ArrayList<>();
    private Command add;
    private Command addIfMax;
    private Command clear;
    private Command countLessThanMinimalPoint;
    private Command executeScript;
    private Command exit;
    private Command head;
    private Command help;
    private Command history;
    private Command info;
    private Command minByDifficulty;
    private Command printAscending;
    private Command removeById;
    private Command save;
    private Command show;
    private Command update;
    private UserManager userManager;

    public CommandsManager(Command add, Command addIfMax,Command clear, Command countLessThanMinimalPoint, Command executeScript,
                           Command exit, Command head, Command help, Command history, Command info, Command minByDifficulty,
                           Command printAscending,Command removeById, Command save , Command show, Command update , UserManager userManager) {
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
        this.exit = exit;
        commands.add(exit);
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
        this.save = save;
        commands.add(save);
        this.show = show;
        commands.add(show);
        this.update = update;
        commands.add(update);

        this.userManager = userManager;
    }

    public String[] getHistory(){
        return listHistory;
    }

    public List<Command> getCommands(){
        return commands;
    }

    /**
     * Добавляет команду в историю.
     * @param command - команда, которую нужно добавить.
     */
    public void addToHistory(String command){
        for (Command command1 : commands){
            if (command1.getName().split(" ")[0].equals(command)){
                for (int i = historySize-1; i>0; i--){
                    listHistory[i] = listHistory[i-1];
                }
                listHistory[0] = command;
            }
        }
    }

    /**
     * Срабатывает, если введённой команды нет в программе.
     * @return Статус команды = false.
     */
    public boolean noCommand(String command){
        System.out.println("Нет команды '"+command+"'. Введите 'help' для вывода списка доступных команд.");
        return false;
    }

    public boolean add(String arg){
        return add.execute(arg);
    }

    public boolean addIfMax(String arg){return addIfMax.execute(arg);}

    public boolean clear(String arg){
        return clear.execute(arg);
    }

    public boolean countLessThanMinimalPoint(String arg){
        return countLessThanMinimalPoint.execute(arg);
    }

    public boolean executeScript(String arg){return executeScript.execute(arg);}

    public boolean exit(String arg){
        return exit.execute(arg);
    }

    public boolean head(String arg){
        return head.execute(arg);
    }

    /**
     * Выводит список доступных команд.
     * @return Статус команды.
     */
    public boolean help(String arg){
        if (help.execute(arg)){
            System.out.println("Список доступных команд:");
            for (Command command : commands){
                System.out.println(command.toString());
            }
            return true;
        } else return false;
    }

    /**
     * Выводит историю команд.
     * @return Статус команды
     */
    public boolean history(String arg){
        if (history.execute(arg)){
            System.out.println("Последние использованные команды:");
            for (String command : listHistory){
                if(command != null) System.out.println(command);
            }
            return true;
        } else return false;
    }

    public boolean info(String arg){
        return info.execute(arg);
    }

    public boolean minByDifficulty(String arg){
        return minByDifficulty.execute(arg);
    }

    public boolean printAscending(String arg){return printAscending.execute(arg);}

    public boolean removeById(String arg){
        return removeById.execute(arg);
    }

    public boolean save(String arg){return save.execute(arg);}

    public boolean show(String arg){
        return show.execute(arg);
    }

    public boolean update(String arg){
        return update.execute(arg);
    }

    /**
     * Позволяет выполнять команды.
     * @param nowCommand - команда, введённая пользователем.
     * @return статус работы программы.
     */
    public int launchCommand(String[] nowCommand){
        switch (nowCommand[0]){
            case "":
                break;
            case "add":
                if (!add(nowCommand[1])) return 1;
                break;
            case "add_if_max":
                if (!addIfMax(nowCommand[1])) return 1;
                break;
            case "clear":
                if (!clear(nowCommand[1])) return 1;
                break;
            case "count_less_than_minimal_point":
                if (!countLessThanMinimalPoint(nowCommand[1])) return 1;
                break;
            case "exit":
                if (!exit(nowCommand[1])) return 1;
                else return 2;
            case "head":
                if (!head(nowCommand[1])) return 1;
                break;
            case "help":
                if (!help(nowCommand[1])) return 1;
                break;
            case "history":
                if (!history(nowCommand[1])) return 1;
                break;
            case "info":
                if (!info(nowCommand[1])) return 1;
                break;
            case "min_by_difficulty":
                if (!minByDifficulty(nowCommand[1])) return 1;
                break;
            case "print_ascending":
                if (!printAscending(nowCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!removeById(nowCommand[1])) return 1;
                break;
            case "save":
                if (!save(nowCommand[1])) return 1;
                break;
            case "show":
                if (!show(nowCommand[1])) return 1;
                break;
            case "update":
                if (!update(nowCommand[1])) return 1;
                break;
            case "execute_script":
                if (!executeScript(nowCommand[1])) return 1;
                else return script(nowCommand[1]);
            default:
                if (!noCommand(nowCommand[0])) return 1;
        }
        return 0;
    }

    /**
     * Позволяет исполнять скрипт.
     * @param arg - имя файла, из которого нужно исполнить скрипт.
     * @return статус работы скрипта.
     */
    public int script(String arg){
        String[] nowCommand = {"",""};
        int status;
        try (Scanner scriptScanner = new Scanner(new File(arg))){
            if (!scriptScanner.hasNextLine()) throw new NoSuchElementException();
            Scanner userScanner = userManager.getUserScanner();
            userManager.setUserScanner(scriptScanner);
            userManager.setFileMode();
            do{
                nowCommand = (scriptScanner.nextLine().trim()+ " ").split(" ",2);
                nowCommand[1] = nowCommand[1].trim();
                System.out.println(nowCommand[0]+" "+nowCommand[1]);
                status = launchCommand(nowCommand);
            } while (status == 0 & scriptScanner.hasNextLine());
            userManager.setUserScanner(userScanner);
            userManager.setUserMode();
            return status;
        } catch (FileNotFoundException e) {
            System.out.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException e){
            System.out.println("Файл пуст!");
        }
        return 1;
    }

}
