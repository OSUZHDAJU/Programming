package data;

/**
 * Перечисление цветов волос.
 */
public enum HairColor {
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