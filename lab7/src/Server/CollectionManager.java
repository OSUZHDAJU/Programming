package Server;

import Global.Exceptions.DBException;
import Global.Exceptions.UserIsNotFoundException;
import Global.Response;
import Global.data.User;
import Global.data.Difficulty;
import Global.data.LabWork;
import Global.DBCollectionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CollectionManager {
    private NavigableSet<LabWork> collection;
    private LocalDateTime initializationTime;
    private static HashMap<String, String> commands;
    private final int historySize = 7;
    private String[] listHistory = new String[historySize];
    private DBCollectionManager dbCollectionManager;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private String initializationTimeString;

    public CollectionManager(DBCollectionManager dbCollectionManager){
        this.dbCollectionManager =dbCollectionManager;

        loadCollection();
    }

    {

        initializationTime = LocalDateTime.now();
        commands = new HashMap<String, String>();
        commands.put("add {element}", "добавить новый элемент в коллекцию");//+
        commands.put("clear", "очистить коллекцию");//+
        commands.put("count_less_than_minimal_point minimalPoint","вывести количество элементов, значение поля minimalPoint которых меньше заданного");//+
        commands.put("execute_script file_name","считать и исполнить скрипт из указанного файла");
        commands.put("exit","завершить программу(без сохранения коллекции)");
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

    public DBCollectionManager getDbCollectionManager() {
        return dbCollectionManager;
    }

    private void loadCollection(){
        try{
            collection = dbCollectionManager.getCollection();
            initializationTime = LocalDateTime.now();
            initializationTimeString = initializationTime.format(format);
            System.out.println("Коллекция загружена.");
        } catch (DBException exception) {
            collection = new TreeSet<>();
            System.out.println("Коллекция не может быть загружена!");
        }
    }

    public synchronized void addToHistory(String command){
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
        Arrays.stream(listHistory).filter(Objects::nonNull).forEach(s1 -> s.append(s1).append("\n"));
        return new Response(s.toString());
    }

    public Response register(User user){
        try{
            if(dbCollectionManager.getDbUserManager().insertUser(user))
                return new Response("Пользователь "+ user.getUsername()+" зарегистрирован.");
            else return new Response("Пользователь "+user.getUsername()+" уже зарегистрирован!");
        } catch (DBException e){
            return new Response("Произошла ошибка при обращении к БД");
        }
    }

    public Response add(LabWork labWork, User user) {
        try {
            labWork.setUser(user);
            collection.add(dbCollectionManager.insertLabWork(labWork, user));
            return new Response("Добавлен объект: " + labWork.toString());
        } catch (DBException e){
            return new Response("Ошибка при добавлении объекта в БД.");
        }
    }

    public Response login(User user){
        Response response;
        try{
            if (dbCollectionManager.getDbUserManager().checkUserByUsernameAndPassword(user))
                response = new Response("Пользователь " +user.getUsername()+" авторизован!");
            else throw new UserIsNotFoundException();
            return response;
        } catch (UserIsNotFoundException e){
         return new Response("Неправильные имя пользователя или пароль!");
        } catch (DBException e){
           return new Response("Ошибка подключения к БД!");
        }
    }

    public synchronized void sort(){
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

    public Response head(){
        return new Response(collection.first().toString());
    }

    public synchronized Response printAscending(){
        sort();
        StringBuilder s = new StringBuilder();
        collection.stream().forEach(labWork -> s.append(labWork.toString()+"\n"));
        return new Response(s.toString());
    }

    public synchronized Response clear(User user){
        for (LabWork labWork: collection) {
            try {
                if (labWork.getUser().getUsername().equals(user.getUsername())) {
                    long id = labWork.getId();
                    collection.remove(labWork);
                    dbCollectionManager.deleteLabWorkById(id);
                }
                return new Response("Ваши данные очищены!");
            } catch (DBException e){
                return new Response("Произошла ошибка при работе с БД.");
            }
        }
        return new Response("Ваши данные очищены!");
    }

    public Response info() {
        return new Response("Тип коллекции: " + collection.getClass().getSimpleName()
                + "\nКоличество объектов: " + collection.size()
                + "\nДата инициализации: " + initializationTimeString);
    }

    public synchronized Response countLessMinimalPoint(long max){
        long count = collection.stream().filter(labWork -> (labWork.getMinimalPoint() != null && max > labWork.getMinimalPoint())).count();
        return new Response("Количество элементов, значение поля minimalPoint которых меньше "+max+" = "+count+"/"+collection.size()+".");
    }

    public synchronized Response getMinByDifficulty(){
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

    public synchronized Response show(){
        StringBuilder s = new StringBuilder();
        collection.forEach(labWork -> s.append(labWork.toString() + "\n"));
        return new Response(s.toString());
    }

    public synchronized Response updateId(LabWork labWork, long id, User user) {
        for (LabWork t : collection) {
            if (t.getId() == id && t.getUser().equals(user)) {
                try {
                    labWork.setId(id);
                    dbCollectionManager.deleteLabWorkById(id);
                    collection.remove(t);
                    collection.add(dbCollectionManager.updateLabWork(labWork,user,id));
                } catch (DBException e){
                    return new Response("Произошла ошибка при работе с БД.");
                }

                break;
            } else return new Response("У вас нет прав доступа к этому элементу!");
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

    public synchronized Response removeById2(long id,User user) {
        LabWork labWork = collection.stream().filter(w -> w.getId() == id).findFirst().orElse(null);
        if (labWork == null) {
            return new Response("Элемент с указанным id не найден.");
        }
        else if(labWork.getUser().getUsername().equals(user.getUsername())) {
            long currentId = labWork.getId();
            try {
                dbCollectionManager.deleteLabWorkById(currentId);
                collection.remove(labWork);
            } catch (DBException e){
                return new Response("Ошибка при работе с БД.");
            }
            return new Response("Элемент с id " + id + " успешно удалён.");
        } else return new Response("У вас нет прав доступа к этому элементу!");
    }
}
