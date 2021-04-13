package Commands;

import Exceptions.IncorrectInputException;
import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.CommandsManager;
import Managers.UserManager;
import data.LabWork;

import java.util.NoSuchElementException;

public class Update extends Command{
    private UserManager userManager;
    private CollectionManager collectionManager;

    public Update(CollectionManager collectionManager, UserManager userManager) {
        super("update id {element}", "обновить значение элемента по его id");
        this.userManager = userManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try {
            if (arg.isEmpty()) throw new WrongArgument();
            long longId = Long.parseLong(arg);
            LabWork newLabWork = null;
            boolean boo = true;
            for (LabWork labWork: collectionManager.getCollection()){
                if (labWork.getId() == longId){
                            newLabWork = new LabWork(
                            labWork.getId(),
                            userManager.askName(),
                            userManager.askCoordinates(),
                            labWork.getCreationDate(),
                            userManager.askMinimalPoint(),
                            userManager.askDescription(),
                            userManager.askTunedInWorks(),
                            userManager.askDifficulty(),
                            userManager.askAuthor()
                    );
                    collectionManager.removeById(longId);
                    boo = false;
                }
            }
            if (boo) throw new NoSuchElementException();
            collectionManager.addToCollection(newLabWork);
            System.out.println("Элемент с id="+longId+" обновлён!");
            return true;
        }catch (WrongArgument e) {
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '" + getName() + "'");
        } catch (IncorrectInputException e){}
        catch (NumberFormatException e){
            System.out.println("error: Неправильный формат аргумента!(id должно быть целым числом)");
        } catch (NoSuchElementException e){
            System.out.println("error: В коллекции нет элемента с таким id!");
        }
        return false;
    }
}
