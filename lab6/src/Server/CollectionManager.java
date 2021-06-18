package Server;

import Global.Response;
import Global.data.Difficulty;
import Global.data.LabWork;
import com.sun.org.apache.regexp.internal.RE;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;


public class CollectionManager {
    private ArrayDeque<LabWork> collection = new ArrayDeque<>();
    private LocalDateTime initializationTime;
    private static HashMap<String, String> commands;
    private FileManager fileManager;
    private int historySize = 7;
    private String[] listHistory = new String[historySize];


    {

        initializationTime = LocalDateTime.now();
        commands = new HashMap<String, String>();
        fileManager = new FileManager("LabaXML.txt");
        fileManager.fileRead();
        commands.put("add {element}", "добавить новый элемент в коллекцию");//+
        commands.put("add_if_max {element}","добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");//+
        commands.put("clear", "очистить коллекцию");//+
        commands.put("count_less_than_minimal_point minimalPoint","вывести количество элементов, значение поля minimalPoint которых меньше заданного");//+
        commands.put("execute_script file_name","считать и исполнить скрипт из указанного файла");
        commands.put("exit","завершить программу(без сохранения коллекции)");
        commands.put("head","вывести первый элемент коллекции");//+
        commands.put("help", "вывести справку по доступным командам");//+
        commands.put("history","вывести последние 7 команд");
        commands.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.");//+
        commands.put("min_by_difficulty","вывести элемент с минимальным значением difficulty");//+
        commands.put("print_ascending","вывести элементы коллекции в порядке возрастания");//+
        commands.put("remove_by_id id", "удалить элемент по его id");//+
        commands.put("save", "сохранить коллекцию в файл");//+
        commands.put("show", "вывести все элементы коллекции");//+
        commands.put("update id {element}", "обновить значение элемента по его id");//+

    }

    public void addToHistory(String command){
        for (Map.Entry<String, String> pair : commands.entrySet()){
            if (pair.getKey().equals(command)){
                for (int i = historySize-1; i>0; i--){
                    listHistory[i] = listHistory[i-1];
                }
                listHistory[0] = command;
            }
        }

    }

    public Response history(){
        StringBuilder s = new StringBuilder();
        s.append("Последние использованные команды:\n");
        Arrays.stream(listHistory).filter(s1 -> s1 != null).forEach(s1 -> s.append(s1 + "\n") );
        return new Response(s.toString());
    }

    public Response add(LabWork labWork){
        labWork.setId(generateNextId());
        collection.add(labWork);
        return new Response("Добавлен объект: " + labWork.toString());
    }

    public Response head(){
        if (!collection.isEmpty()) {
            return new Response(collection.getFirst().toString());
        }  else return new Response("Коллекция пуста!");
    }

    public void read(){
        this.collection = fileManager.fileRead();
    }

    public ArrayDeque<LabWork> getCollection(){
        return collection;
    }

    public void save(){
        fileManager.fileWrite(collection);
    }

    public Response addIfMax(LabWork labWork){
        labWork.setId(generateNextId());
        sort();
        if (labWork.compareTo(collection.getLast()) > 0){
            collection.add(labWork);
            return new Response("Добавлен объект: " + labWork.toString());
        }
        return new Response("Объект не удовлетворяет ксловию добавления!");
    }

    public void sort(){
        try{
            ArrayList<LabWork> labWorks = new ArrayList<>();
            labWorks.addAll(collection);
            Collections.sort(labWorks);
            collection.clear();
            collection.addAll(labWorks);
        } catch (NullPointerException e){
            System.out.println("Коллекция пуста!");
        }
    }

    public Response printAscending(){
        sort();
        StringBuilder s = new StringBuilder();
        collection.stream().forEach(labWork -> s.append(labWork.toString()+"\n"));
        return new Response(s.toString());
    }

    public Response clear(){
        collection.clear();
        return new Response("Коллекция очищена!");
    }

    public Response info() {
        return new Response("Тип коллекции: " + collection.getClass().getSimpleName()
                + "\nКоличество объектов: " + collection.size()
                + "\nДата инициализации: " + initializationTime);
    }

    public void removeById(long id, boolean anal){
        boolean deleted = false;
        for (LabWork labWork: collection){
            if (labWork.getId() == id){
                deleted = true;
                collection.remove(labWork);
                if (anal) System.out.println("Объект с id = "+id+" удалён!");
                break;
            }
        }
        if (!deleted && anal) System.out.println("Объекта с таким id в коллекции нет!");
    }

    public Long generateNextId(){
        if (collection.isEmpty()) {
            return 1L;
        }
        sortById();
        return collection.getLast().getId()+1;
    }

    public Response countLessMinimalPoint(long max){
        long count = collection.stream().filter(labWork -> (labWork.getMinimalPoint() != null && max > labWork.getMinimalPoint())).count();
        return new Response("Количество элементов, значение поля minimalPoint которых меньше "+max+" = "+count+"/"+collection.size()+".");
    }

    public void sortById(){
        long lastMin = 0L;
        long min;
        long sortId = 0L;
        for (int i = 0; i < collection.size(); i++) {
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
            removeById(lastMin,false);
        }
    }

    public Response getMinByDifficulty(){
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
        return new Response("Элемент с минимальным значением difficulty : " + min);
    }

    public Response show(){
        StringBuilder s = new StringBuilder();
        collection.forEach(labWork -> s.append(toString() + "\n"));
        return new Response(s.toString());
    }

    public Response updateId(LabWork labWork, long id) {
        for (LabWork t : collection) {
            if (t.getId() == id) {
                collection.remove(t);
                collection.add(labWork);
                break;
            }
        } return new Response("Элемент с id="+id+" обновлён!");
    }

    public Response help(){
        StringBuilder s = new StringBuilder();
        Set keySet = commands.keySet();
        keySet.stream().forEach(key -> s.append(key + " - " + commands.get(key) + "\n" ));
        return new Response(s.toString());
    }

    public void help(String command){
        if (command.equals("help")) {
            StringBuilder s = new StringBuilder();
            Set keySet = commands.keySet();
            keySet.stream().forEach(key -> s.append(key + " - " + commands.get(key) + "\n"));
            System.out.println(s.toString());
        }
    }

    public void info(String command){
        if(command.equals("info")){
            System.out.println("Тип коллекции: " + collection.getClass().getSimpleName()
                    + "\nКоличество объектов: " + collection.size()
                    + "\nДата инициализации: " + initializationTime);
        }
    }

    public Response removeById2(long id) {
        LabWork labWork = collection.stream().filter(w -> w.getId() == id).findFirst().orElse(null);
        if (labWork == null) {
            return new Response("Элемент с указанным id не найден.");
        }
        else {
            collection.remove(labWork);
            return new Response("Элемент с id " + id + " успешно удалён.");
        }
    }
}
