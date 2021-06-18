package Global;

import Global.Exceptions.InvalidAmountOfArgumentsException;
import Global.Exceptions.UniqueException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;


public class Parsers {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean verify(String[] cmdSplit, int argsAmount) throws InvalidAmountOfArgumentsException {
        boolean ver = cmdSplit.length == argsAmount + 1;
        if (!ver) throw new InvalidAmountOfArgumentsException();
        return true;
    }


    public static int parseMinimalPoint(String s) throws UniqueException {
        int height = Integer.parseInt(s);
        if (!(height > 0)) throw new UniqueException("Минимальная точка должна быть положительной.");
        return height;
    }

    public static LocalDateTime parseTheLocalDateTime(String s) throws UniqueException{
        LocalDateTime dateTime = null;
        LocalDate input = parseTheDate(s);
        try {
            dateTime = LocalDateTime.of(input, LocalTime.of(0,0));
        }catch (NullPointerException e){
            dateTime = null;
        }
        if(dateTime == null) throw new UniqueException("Некорректный ввод даты");
        return dateTime;

    }
    public static LocalDate parseTheDate(String s)  throws UniqueException {
        if (s == null || s.isEmpty()) {
            return null;
        }
        LocalDate date = LocalDate.parse(s, formatter);
        if(date == null) throw new UniqueException("Некоректный ввод даты");
        return date;
    }



    public static float parseY(String s) throws UniqueException{
        float salary = Float.parseFloat(s);
        if (salary>640) throw new UniqueException("Максимальное значение y = 640");
        if (!(salary < Float.MAX_VALUE)) throw new UniqueException("Число не входит в область типа float");
        return salary;
    }

}