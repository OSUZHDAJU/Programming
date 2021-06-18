package Global.data;

import java.io.Serializable;

/**
 * Перечисление цветов глаз.
 */
public enum EyeColor implements Serializable {
    BLUE,
    YELLOW,
    ORANGE;

    /**
     * Создаёт список строковых констант цветов глаз.
     * @return Строку со всеми константами.
     */
    public static String list(){
        String list = "";
        for (EyeColor eyeColor : values()){
            list += eyeColor.name() + ", ";
        }
        return list.substring(0, list.length()-2) + ".";
    }
}