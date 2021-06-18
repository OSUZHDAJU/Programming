package Server;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;

import Global.data.*;

public class FileManager {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Читает коллекцию из файла.
     * @return Коллекцию типа LabWork.
     */
    public ArrayDeque<LabWork> fileRead() {
        if (fileName != null) {
            try {
                ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
                File xmlFile = new File(fileName);
                if (!xmlFile.canRead()) throw new Exception();
                Reader fileReader = new FileReader(xmlFile);
                BufferedReader bufReader = new BufferedReader(fileReader);
                String line = bufReader.readLine();
                while (line != null) {
                    if (line.equals("<LabWork>")) {
                        long id;
                        String name;
                        long xCoor;
                        float yCoor;
                        LocalDateTime creationDate;
                        long minimalPoint;
                        String description;
                        int tunedInWorks;
                        Difficulty difficulty;
                        String authorName;
                        LocalDateTime birthday;
                        EyeColor eyeColor;
                        HairColor hairColor;
                        Country nationality;
                        long xLoc;
                        double yLoc;
                        float zLoc;

                        line = bufReader.readLine();
                        String substringId = line.substring(4, line.length() - 5);
                        id = Long.parseLong(substringId);

                        line = bufReader.readLine();
                        name = line.substring(6, line.length() - 7);

                        bufReader.readLine();
                        line = bufReader.readLine();
                        String substringX = line.substring(3, line.length() - 4);
                        xCoor = Long.parseLong(substringX);

                        line = bufReader.readLine();
                        String substringY = line.substring(3, line.length() - 4);
                        yCoor = Float.parseFloat(substringY);

                        bufReader.readLine();
                        line = bufReader.readLine();
                        String substringCreationDate = line.substring(14, line.length() - 15);
                        LocalDate localCreationDate = LocalDate.parse(substringCreationDate, formatter);
                        creationDate = LocalDateTime.of(localCreationDate, LocalTime.of(0, 0));

                        line = bufReader.readLine();
                        String substringMinimalPoint = line.substring(14, line.length() - 15);
                        minimalPoint = Long.parseLong(substringMinimalPoint);

                        line = bufReader.readLine();
                        description = line.substring(13, line.length() - 14);

                        line = bufReader.readLine();
                        String substringTunedInWorks = line.substring(14, line.length() - 15);
                        tunedInWorks = Integer.parseInt(substringTunedInWorks);

                        line = bufReader.readLine();
                        String substringDifficulty = line.substring(12, line.length() - 13);
                        difficulty = Difficulty.valueOf(substringDifficulty.toUpperCase());

                        bufReader.readLine();
                        line = bufReader.readLine();
                        authorName = line.substring(6, line.length() - 7);

                        line = bufReader.readLine();
                        String substringBirthday = line.substring(10, line.length() - 11);
                        LocalDate localBirthdayDate = LocalDate.parse(substringBirthday, formatter);
                        birthday = LocalDateTime.of(localBirthdayDate, LocalTime.of(0, 0));

                        line = bufReader.readLine();
                        String substringEyeColor = line.substring(10, line.length() - 11);
                        eyeColor = EyeColor.valueOf(substringEyeColor.toUpperCase());

                        line = bufReader.readLine();
                        String substringHairColor = line.substring(11, line.length() - 12);
                        hairColor = HairColor.valueOf(substringHairColor.toUpperCase());

                        line = bufReader.readLine();
                        String substringNationality = line.substring(13, line.length() - 14);
                        nationality = Country.valueOf(substringNationality.toUpperCase());

                        bufReader.readLine();
                        line = bufReader.readLine();
                        String substringXLoc = line.substring(3, line.length() - 4);
                        xLoc = Long.parseLong(substringXLoc);

                        line = bufReader.readLine();
                        String substringYLoc = line.substring(3, line.length() - 4);
                        yLoc = Double.parseDouble(substringYLoc);

                        line = bufReader.readLine();
                        String substringZLoc = line.substring(3, line.length() - 4);
                        zLoc = Float.parseFloat(substringZLoc);

                        LabWork labWork = new LabWork(id, name, new Coordinates(xCoor, yCoor), creationDate, minimalPoint, description, tunedInWorks, difficulty, new Person(authorName, birthday, eyeColor, hairColor, nationality, new Location(xLoc, yLoc, zLoc)));
                        collection.add(labWork);
                    }
                    line = bufReader.readLine();
                }
                return collection;
            } catch (FileNotFoundException e) {
                System.out.println("Загрузочный файл не найден!");
            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода!");
            } catch (Exception e){
                System.out.println("Загрузочный файл недоступен для чтения");
            }
        } else System.out.println("Переменная окружения с именем файла не найдена!");
        return new ArrayDeque<LabWork>();
    }

    /**
     * Приводит однозначные числа к двузначному виду.
     * @return Двузначное число.
     */
    private static String formate(int valor) {
        return (valor < 10 ? "0" : "") + valor;
    }

    /**
     * Записывает коллекцию в файл.
     * @param collection - коллекция, которую нужно записать.
     * @return Статус записи коллекции.
     */
    public boolean fileWrite(ArrayDeque<LabWork> collection){
        try {
            File xmlFile = new File(fileName);
            if (!xmlFile.canWrite()) throw new Exception();
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(xmlFile) , "utf-8"));
            String line = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            writer.write(line+System.lineSeparator());
            for (LabWork labWork : collection) {
                line = "<LabWork>";
                writer.write(line+System.lineSeparator());
                line = "<id>" + String.valueOf(labWork.getId()) + "</id>";
                writer.write(line+System.lineSeparator());
                line = "<name>" + String.valueOf(labWork.getName()) + "</name>";
                writer.write(line+System.lineSeparator());
                line = "<Coordinates>";
                writer.write(line+System.lineSeparator());
                line = "<x>"+String.valueOf(labWork.getCoordinates().getX())+"</x>";
                writer.write(line+System.lineSeparator());
                line = "<y>"+String.valueOf(labWork.getCoordinates().getY())+"</y>";
                writer.write(line+System.lineSeparator());
                line = "</Coordinates>";
                writer.write(line+System.lineSeparator());
                line = "<creationDate>"+labWork.getCreationDate().getYear()+"-"+formate(labWork.getCreationDate().getMonthValue())+"-"+formate(labWork.getCreationDate().getDayOfMonth())+"</creationDate>";
                //line = ""+labWork.getCreationDate();
                writer.write(line+System.lineSeparator());
                line = "<minimalPoint>"+String.valueOf(labWork.getMinimalPoint())+"</minimalPoint>";
                writer.write(line+System.lineSeparator());
                line = "<description>"+labWork.getDescription() +"</description>";
                writer.write(line+System.lineSeparator());
                line = "<tunedInWorks>"+String.valueOf(labWork.getTunedInWorks())+"</tunedInWorks>";
                writer.write(line+System.lineSeparator());
                line = "<difficulty>"+labWork.getDifficulty().toString()+"</difficulty>";
                writer.write(line+System.lineSeparator());
                line = "<author>";
                writer.write(line+System.lineSeparator());
                line = "<name>"+labWork.getAuthor().getName()+"</name>";
                writer.write(line+System.lineSeparator());
                line = "<birthday>"+labWork.getAuthor().getBirthday().getYear()+"-"+formate(labWork.getAuthor().getBirthday().getMonthValue())+"-"+formate(labWork.getAuthor().getBirthday().getDayOfMonth())+"</birthday>";
                writer.write(line+System.lineSeparator());
                line = "<eyeColor>"+labWork.getAuthor().getEyeColor().toString()+"</eyeColor>";
                writer.write(line+System.lineSeparator());
                line = "<hairColor>"+labWork.getAuthor().getHairColor().toString()+"</hairColor>";
                writer.write(line+System.lineSeparator());
                line = "<nationality>"+labWork.getAuthor().getNationality().toString()+"</nationality>";
                writer.write(line+System.lineSeparator());
                line = "<location>";
                writer.write(line+System.lineSeparator());
                line = "<x>"+String.valueOf(labWork.getAuthor().getLocation().getX())+"</x>";
                writer.write(line+System.lineSeparator());
                line = "<y>"+String.valueOf(labWork.getAuthor().getLocation().getY())+"</y>";
                writer.write(line+System.lineSeparator());
                line = "<z>"+String.valueOf(labWork.getAuthor().getLocation().getZ())+"</z>";
                writer.write(line+System.lineSeparator());
                line = "</location>";
                writer.write(line+System.lineSeparator());
                line = "</author>";
                writer.write(line+System.lineSeparator());
                line = "</LabWork>";
                writer.write(line+System.lineSeparator());
            }
            writer.close();
            System.out.println("Коллекция записана");
            return true;
        } catch (IOException e){
            System.out.println("Ошибка ввода-вывода!");
        }
        catch (Exception e){
            System.out.println("Файл недоступен для записи.");
        }
        return false;
    }

}
