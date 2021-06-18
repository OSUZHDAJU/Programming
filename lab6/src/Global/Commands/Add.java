package Global.Commands;

import Global.Exceptions.*;
import Global.*;
import Global.data.*;

import java.time.LocalDateTime;

/**
 * Команда 'add'. Добавляет новый элемнт в коллекцию.
 */
public class Add extends Command{
    private UserManager userManager;
    private LabWork labWork;

    public Add(UserManager userManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.userManager = userManager;
    }

    /**
     * Выполняет команду.
     * @param commandSplit - аргументы пользователя и сама команда.
     * @return Статус команды.
     */
    public Request execute (String[] commandSplit){
        try{
            Parsers.verify(commandSplit, 1);
            String name = userManager.askName();
            Coordinates coordinates = userManager.askCoordinates();
            LocalDateTime localDateTime = LocalDateTime.now();
            Long minimalPoint = userManager.askMinimalPoint();
            String description = userManager.askDescription();
            Integer tunedInWorks = userManager.askTunedInWorks();
            Difficulty difficulty = userManager.askDifficulty();
            Person author = userManager.askAuthor();
            LabWork labWork = new LabWork((long)-1,name,coordinates,localDateTime,minimalPoint,description,tunedInWorks,difficulty,author);
            this.labWork = labWork;
            return getRequest();
        } catch (InvalidAmountOfArgumentsException e){
            System.out.println("Использование команды: '"+ getName() + "'");
        }
        catch (IncorrectInputException e) {
            e.printStackTrace();
        }
        return new Request(null);
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
