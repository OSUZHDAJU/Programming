package data;

/**
 * Перечисление типов сложности.
 */
public enum Difficulty {
    VERY_HARD,
    INSANE,
    HOPELESS;

    /**
     * Создаёт список строковых констант сложности.
     * @return Строку со всеми константами.
     */
    public static String list(){
        String list = "";
        for (Difficulty difficulty : values()){
            list += difficulty.name() + ", ";
        }
        return list.substring(0, list.length()-2) + ".";
    }

}