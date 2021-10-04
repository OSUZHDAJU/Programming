package Global.data;

import java.io.Serializable;

/**
 * Перечисление цветов волос.
 */
public enum HairColor implements Serializable {
    BLACK,
    BLUE,
    YELLOW,
    ORANGE;

    /**
     * Создаёт список строковых констант цветов волос.
     * @return Строку со всеми константами.
     */
    public static String list(){
        String list = "";
        for (HairColor hairColor : values()){
            list += hairColor.name() + ", ";
        }
        return list.substring(0, list.length()-2) + ".";
    }
}