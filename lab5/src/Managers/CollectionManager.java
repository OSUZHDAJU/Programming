package Managers;

import data.Difficulty;
import data.LabWork;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс отвечающий за работу с коллекцией.
 */
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

    /**
     * @return Дату инициализации коллекции.
     */
    public String getInitializationTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return initializationTime.format(formatter);
    }

    /**
     * @param max - значение ниже которого должны быть значения элементов.
     * @return Количество элементов, значение поля minimalPoint которых меньше введённого.
     */
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

    /**
     * @return Элемент, значение поля Difficulty которого минимально.
     */
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

    /**
     * @return количество элементов в коллекции.
     */
    public int getSize(){
        return collection.size();
    }

    /**
     * @return Первый элемент коллекции.
     */
    public LabWork firstElement(){
        if (collection.isEmpty()) return null;
        return collection.getFirst();
    }

    /**
     * Добавляет элемент в коллекцию.
     * @param labWork - элемент который нужно добавить.
     */
    public void addToCollection(LabWork labWork){
        collection.add(labWork);
    }

    /**
     * @return id нового элемента
     */
    public Long generateNextId(){
        if (collection.isEmpty()) {
            return 1L;
        }
        sortById();
        return collection.getLast().getId()+1;
    }

    /**
     * Удаляет элемент по его id.
     * @param id
     */
    public void removeById(long id){
        for (LabWork labWork: collection){
            if (labWork.getId() == id){
                collection.remove(labWork);
                break;
            }
        }
    }

    /**
     * Сортирует коллекцию по увеличению id.
     */
    public void sortById(){
        long lastMin = 0L;
        long min;
        long sortId = 0L;
        for (int i = 0; i < getSize(); i++) {
            min = Long.MAX_VALUE;
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

    /**
     * Сортирует коллекцию по умолчанию.
     */
    public void sort() {
        try {
            ArrayList<LabWork> labWorkList = new ArrayList<LabWork>();
            labWorkList.addAll(collection);
            Collections.sort(labWorkList);
            collection.clear();
            for (LabWork labWork : labWorkList){
                collection.add(labWork);
            }
        } catch (NullPointerException e){
            System.out.println("Коллекция пуста!");
        }
    }

    /**
     * Очищает коллекцию.
     */
    public void clearCollection(){
        collection.clear();
    }

    /**
     * Загружает коллекцию из файла.
     */
    private void loadCollection(){
        collection = fileManager.fileRead();
    }

}
