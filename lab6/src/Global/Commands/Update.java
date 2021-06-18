package Global.Commands;


import Global.Exceptions.IncorrectInputException;
import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Parsers;
import Global.Request;
import Global.data.*;
import Global.UserManager;


import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class Update extends Command{
    private UserManager userManager;
    private LabWork labWork;
    private Long id;

    public Update(UserManager userManager) {
        super("update id {element}", "обновить значение элемента по его id");
        this.userManager = userManager;
    }

    public Request execute(String[] arg){
        try {
            Parsers.verify(arg,1);
            this.id = Long.parseLong(arg[1]);
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
        }catch (InvalidAmountOfArgumentsException e) {
            System.out.println("Использование команды: '" + getName() + "'");
        } catch (IncorrectInputException e){ }
        catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(id должно быть целым числом)");
        } catch (NoSuchElementException e){
            System.out.println("error: В коллекции нет элемента с таким id!");
        }
        return null;
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public Long getId() {
        return id;
    }
}
