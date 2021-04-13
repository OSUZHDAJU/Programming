package Managers;

import data.*;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;

public class FileManager {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public ArrayDeque<LabWork> fileRead() {
        if (fileName != null) {
            try {
                    ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
                    File xmlFile = new File(fileName);
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
            }
        } else System.out.println("Переменная окружения с именем файла не найдена!");
        return new ArrayDeque<LabWork>();
    }

    public boolean fileWrite(ArrayDeque<LabWork> collection){
        String line;
        line = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
        for (LabWork labWork : collection){
            line = "<LabWork>";

            line = "<id>"+labWork.getId()+"</id>";

            line = "<name>"+labWork.getName()+"</name>";

            line = "<Coordinates>";
        } return false;
    }

    @Override
    public String toString() {
        return "FileManager(класс для работы с файлом)";
    }
}
