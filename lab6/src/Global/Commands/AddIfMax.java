package Global.Commands;


import Global.Exceptions.IncorrectInputException;
import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Exceptions.WrongArgument;
import Global.Parsers;
import Global.Request;
import Global.UserManager;
import Global.data.Coordinates;
import Global.data.Difficulty;
import Global.data.LabWork;
import Global.data.Person;


import java.time.LocalDateTime;

public class AddIfMax extends Command{
    private UserManager userManager;
    private LabWork labWork;

    public AddIfMax(UserManager userManager) {
        super("add_if_max {element}","добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
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
