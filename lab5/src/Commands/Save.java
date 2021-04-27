package Commands;

import Exceptions.EmptyCollection;
import Exceptions.WrongArgument;
import Managers.CollectionManager;
import Managers.FileManager;

import java.io.File;

public class Save extends Command{
    private FileManager fileManager;
    private CollectionManager collectionManager;

    public Save(FileManager fileManager, CollectionManager collectionManager){
        super("save", "сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String arg){
        try{
            if (!arg.isEmpty()) throw new WrongArgument();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollection();
            if (!fileManager.fileWrite(collectionManager.getCollection())) throw new Exception();
            System.out.println("Коллекция сохранена в файл.");
            return true;
        } catch (WrongArgument e){
            System.out.println("Неправильный аргумент!");
            System.out.println("Использование команды: '"+ getName() + "'");
        } catch (EmptyCollection e){
            System.out.println("Коллекция пуста!");
        } catch (Exception e){};
        return false;
    }

}
