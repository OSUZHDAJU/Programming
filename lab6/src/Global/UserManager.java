package Global;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import Global.Exceptions.*;
import Global.data.*;
public class UserManager {
    private final float max_y = 640;
    private final long min_minimalPoint = 0;

    private Scanner userScanner;
    private boolean mode; // if TRUE then "User Mode", else "File Mode"

    public UserManager(Scanner userScanner){
        this.userScanner=userScanner;
        mode=true;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public void setUserMode() {
        mode = true;
    }

    public void setFileMode() {
        mode = false;
    }

    public Scanner getUserScanner(){return userScanner;}

    public String askAuthorName() throws IncorrectInputException {
        String name;
        while (true){
            try{
                System.out.println("Введите имя автора:");
                name = userScanner.nextLine().trim();
                if (!mode) System.out.println(name);
                if (name.equals("")) throw new ShouldNotBeEmptyException();
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Имя не распознано!");
                if(!mode) throw new IncorrectInputException();
            } catch (ShouldNotBeEmptyException e){
                System.out.println("error: Имя не может быть пустым!");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return name;
    }

    public String askName() throws IncorrectInputException {
        String name;
        while (true){
            try{
                System.out.println("Введите имя:");
                name = userScanner.nextLine().trim();
                if (!mode) System.out.println(name);
                if (name.equals("")) throw new ShouldNotBeEmptyException();
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Имя не распознано!");
                if(!mode) throw new IncorrectInputException();
            } catch (ShouldNotBeEmptyException e){
                System.out.println("error: Имя не может быть пустым!");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return name;
    }

    public Coordinates askCoordinates() throws IncorrectInputException {
        long x;
        float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    public long askX() throws IncorrectInputException {
        String strX;
        long x;
        while (true){
            try{
                System.out.println("Введите координату X (формат: целое число):");
                strX = userScanner.nextLine().trim();
                if (!mode) System.out.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Координата X не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (NumberFormatException e){
                System.out.println("error: Неправильный формат строки!(X должно быть целым числом)");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return x;
    }

    public float askY() throws IncorrectInputException {
        String strY;
        float y;
        while (true){
            try{
                System.out.println("Введите координату Y <= "+ max_y +" (формат: число с плавающей точкой):");
                strY = userScanner.nextLine().trim();
                if (!mode) System.out.println(strY);
                y = Float.parseFloat(strY);
                if(y > max_y) throw new OutOfBoundsException();
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Координата Y не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (OutOfBoundsException e){
                System.out.println("error: Координата Y не может превышать "+max_y+"!");
                if (!mode) throw new IncorrectInputException();
            }
            catch (NumberFormatException e){
                System.out.println("error: Неправильный формат строки!(Y должно быть числом)");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return y;
    }

    public long askMinimalPoint() throws IncorrectInputException {
        String strPoint;
        long point;
        while (true){
            try{
                System.out.println("Введите минимальную точку (формат: целое число):");
                strPoint = userScanner.nextLine().trim();
                if (!mode) System.out.println(strPoint);
                point = Long.parseLong(strPoint);
                if(point<min_minimalPoint) throw new OutOfBoundsException();
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Минимальная точка не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (OutOfBoundsException e){
                System.out.println("error: Минимальная точка не может быть меньше "+min_minimalPoint+"!");
                if(!mode) throw new IncorrectInputException();
            }
            catch (NumberFormatException e){
                System.out.println("error: Неправильный формат строки!(должно быть целое число)");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return point;
    }

    public String askDescription() throws IncorrectInputException {
        String description;
        while (true){
            try{
                System.out.println("Введите описание:");
                description = userScanner.nextLine().trim();
                if (!mode) System.out.println(description);
                if (description.equals("")) throw new ShouldNotBeEmptyException();
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Описание не распознано!");
                if(!mode) throw new IncorrectInputException();
            } catch (ShouldNotBeEmptyException e){
                System.out.println("error: Описание не может быть пустым!");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return description;
    }

    public int askTunedInWorks() throws IncorrectInputException {
        String strTIW;
        int TIW;
        while (true){
            try{
                System.out.println("Готовность работать(формат: число 1-готов; 0 или пустая строка-не готов):");
                strTIW = userScanner.nextLine().trim();
                if (!mode) System.out.println(strTIW);
                if (strTIW.equals("")) strTIW = "0";
                TIW = Integer.parseInt(strTIW);
                if (TIW != 0){
                    if (TIW != 1) throw new NumberFormatException();
                }
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Готовность к работе не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (NumberFormatException e){
                System.out.println("error: Неправильный формат(должно быть число 1-готов; 0 или пустая строка-не готов)!");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return TIW;
    }

    public Difficulty askDifficulty() throws IncorrectInputException{
        String strDifficulty;
        Difficulty difficulty;
        while(true){
            try{
                System.out.println("Введите сложность из списка("+ Difficulty.list()+"):");
                strDifficulty = userScanner.nextLine().trim();
                if (!mode) System.out.println(strDifficulty);
                difficulty = Difficulty.valueOf(strDifficulty.toUpperCase());
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Сложность не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (IllegalArgumentException e) {
                System.out.println("error: Сложности нет в списке("+Difficulty.list()+")!");
                if (!mode) throw new IncorrectInputException();
            }
        }
        return difficulty;
    }

    public Person askAuthor() throws IncorrectInputException{
        String name;
        java.time.LocalDateTime birthday;
        EyeColor eyeColor;
        HairColor hairColor;
        Country nationality;
        Location location;
        name = askAuthorName();
        birthday = askBirthday();
        eyeColor = askEyeColor();
        hairColor = askHairColor();
        nationality = askNationality();
        location = askLocation();
        return new Person(name, birthday, eyeColor, hairColor, nationality, location);
    }

    public LocalDateTime askBirthday() throws IncorrectInputException{
        String strBirthday;
        LocalDateTime birthday;
        String regex= "^\\d{4}-\\d{2}-\\d{2}$";
        while(true){
            try{
                System.out.println("Введите дату рождения(формат: ГГГГ-ММ-ДД):");
                strBirthday = userScanner.nextLine().trim();
                if (!mode) System.out.println(strBirthday);
                //if (!Pattern.matches(regex,strBirthday)) throw new IncorrectDateFormatException();
                if (!strBirthday.matches(regex)) throw new IncorrectDateFormatException();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthdayLocalDate = LocalDate.parse(strBirthday, formatter);
                birthday = LocalDateTime.of(birthdayLocalDate, LocalTime.of(0 , 0) );
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Дата рождения не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (DateTimeException e) {
                System.out.println("error: Дата указана неверно!");
                if (!mode) throw new IncorrectInputException();
            } catch (IncorrectDateFormatException e){
                System.out.println("error: Формат даты указан неверно(должно быть: ГГГГ-ММ-ДД)!");
                if (!mode) throw new IncorrectInputException();
            }
        }
        return birthday;
    }

    public EyeColor askEyeColor() throws IncorrectInputException{
        String strEyeColor;
        EyeColor eyeColor;
        while(true){
            try{
                System.out.println("Введите цвет глаз из списка("+ EyeColor.list()+"):");
                strEyeColor = userScanner.nextLine().trim();
                if (!mode) System.out.println(strEyeColor);
                eyeColor = EyeColor.valueOf(strEyeColor.toUpperCase());
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Цвет глаз не распознан!");
                if(!mode) throw new IncorrectInputException();
            } catch (IllegalArgumentException e) {
                System.out.println("error: Цвета глаз нет в списке("+EyeColor.list()+")!");
                if (!mode) throw new IncorrectInputException();
            }
        }
        return eyeColor;
    }

    public HairColor askHairColor() throws IncorrectInputException{
        String strHairColor;
        HairColor hairColor;
        while(true){
            try{
                System.out.println("Введите цвет волос из списка("+ HairColor.list()+"):");
                strHairColor = userScanner.nextLine().trim();
                if (!mode) System.out.println(strHairColor);
                hairColor = HairColor.valueOf(strHairColor.toUpperCase());
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Цвет волос не распознан!");
                if(!mode) throw new IncorrectInputException();
            } catch (IllegalArgumentException e) {
                System.out.println("error: Цвета волос нет в списке("+HairColor.list()+")!");
                if (!mode) throw new IncorrectInputException();
            }
        }
        return hairColor;
    }

    public Country askNationality() throws IncorrectInputException{
        String strNationality;
        Country nationality;
        while(true){
            try{
                System.out.println("Введите национальность из списка("+ Country.list()+"):");
                strNationality = userScanner.nextLine().trim();
                if (!mode) System.out.println(strNationality);
                nationality = Country.valueOf(strNationality.toUpperCase());
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Национальность не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (IllegalArgumentException e) {
                System.out.println("error: Национальности нет в списке("+Country.list()+")!");
                if (!mode) throw new IncorrectInputException();
            }
        }
        return nationality;
    }

    public Location askLocation() throws IncorrectInputException{
        long x;
        double y;
        float z;
        x = askX();
        y = askYdouble();
        z = askZ();
        return new Location(x, y, z);
    }

    public double askYdouble() throws IncorrectInputException {
        String strY;
        double y;
        while (true){
            try{
                System.out.println("Введите координату Y (формат: целое число ):");
                strY = userScanner.nextLine().trim();
                if (!mode) System.out.println(strY);
                y = Double.parseDouble(strY);
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Координата Y не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (NumberFormatException e){
                System.out.println("error: Неправильный формат строки!(Y должно быть числом)");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return y;
    }

    public float askZ() throws IncorrectInputException {
        String strZ;
        float z;
        while (true){
            try{
                System.out.println("Введите координату Z (формат: число с плавающей точкой):");
                strZ = userScanner.nextLine().trim();
                if (!mode) System.out.println(strZ);
                z = Float.parseFloat(strZ);
                break;
            } catch (NoSuchElementException e){
                System.out.println("error: Координата Z не распознана!");
                if(!mode) throw new IncorrectInputException();
            } catch (NumberFormatException e){
                System.out.println("error: Неправильный формат строки!(Z должно быть числом)");
                if(!mode) throw new IncorrectInputException();
            }
        }
        return z;
    }

}

