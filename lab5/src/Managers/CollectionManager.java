package Managers;

import data.Difficulty;
import data.LabWork;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;

public class CollectionManager {
    private ArrayDeque<LabWork> collection = new ArrayDeque<>();
    private FileManager fileManager;
    private LocalDateTime initializationTime;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        loadCollection();
        initializationTime = setInitializationTime();
    }

    private LocalDateTime setInitializationTime(){
        return LocalDateTime.now();
    }

    public String getInitializationTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return initializationTime.format(formatter);
    }

    public long countLessThanMinimalPoint(long max){
        long count = 0;
        for (LabWork labWork : collection){
            if (labWork.getMinimalPoint() < max){
                count+=1;
            }
        }
        return count;
    }

    public ArrayDeque<LabWork> getCollection() {
        return collection;
    }

    public String getCollectionType(){
        return collection.getClass().getTypeName();
    }

    public LabWork getMinByDifficulty(){
        boolean boo = false;
        LabWork min = null;
        for (LabWork labWork : collection){
                if (labWork.getDifficulty().equals(Difficulty.VERY_HARD)){
                    boo = true;
                    min = labWork;
                    break;
                }
        }
        if (!boo){
            for (LabWork labWork : collection){
                    if (labWork.getDifficulty().equals(Difficulty.INSANE)){
                        boo = true;
                        min = labWork;
                        break;
                    }
            }
        }
        if (!boo){
            for (LabWork labWork : collection){
                if (labWork.getDifficulty().equals(Difficulty.HOPELESS)){
                    min = labWork;
                    break;
                }
            }
        }
        return min;
    }

    public int getSize(){
        return collection.size();
    }

    public LabWork firstElement(){
        if (collection.isEmpty()) return null;
        return collection.getFirst();
    }

    public void addToCollection(LabWork labWork){
        collection.add(labWork);
    }

    public Long generateNextId(){
        if (collection.isEmpty()) {
            return 1L;
        }
        sortById();
        return collection.getLast().getId()+1;
    }

    public void removeById(long id){
        for (LabWork labWork: collection){
            if (labWork.getId() == id){
                collection.remove(labWork);
                break;
            }
        }
    }

    public void sortById(){
        long lastMin = 0L;
        long min;
        long sortId = 0L;
        for (int i = 0; i < getSize(); i++) {
            min = 9223372036854775807L;
            for (LabWork labWork : collection){
                long id1 = labWork.getId();
                if (id1 <= min & id1 > lastMin){
                    min = id1;
                    sortId = id1;
                }
            }
            lastMin = sortId;
            for (LabWork labWork : collection){
                if (labWork.getId() == lastMin){
                    collection.addLast(new LabWork(
                            labWork.getId(),
                            labWork.getName(),
                            labWork.getCoordinates(),
                            labWork.getCreationDate(),
                            labWork.getMinimalPoint(),
                            labWork.getDescription(),
                            labWork.getTunedInWorks(),
                            labWork.getDifficulty(),
                            labWork.getAuthor()));
                    break;
                }
            }
            removeById(lastMin);
        }
    }

    public void sort(){
        for (int i = 0; i < getSize(); i++){
            for (LabWork labWork : collection){
                
            }
        }
    }


    public void clearCollection(){
        collection.clear();
    }

    private void loadCollection(){
        collection = fileManager.fileRead();
    }

    @Override
    public String toString() {
        return "CollectionManager(класс для работы с коллекцией)";
    }
}
