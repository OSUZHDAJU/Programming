package data;

/**
 * Перечисление типов национальности.
 */

public enum Country {
    UNITED_KINGDOM,
    INDIA,
    ITALY,
    JAPAN;

    /**
     * Создаёт список строковых констант национальности.
      * @return Строку со всеми константами.
     */
    public static String list(){
        String list = "";
        for (Country country : values()){
            list += country.name() + ", ";
        }
    return list.substring(0, list.length()-2) + ".";
    }
}