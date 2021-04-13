package Commands;

import Exceptions.WrongArgument;


public class Help extends Command{

    public Help() {
        super("help", "вывести справку по доступным командам");
    }

    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new WrongArgument();
            return true;
        } catch (WrongArgument e) {
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        }
        return false;
    }
}
