package data;

public enum Country {
    UNITED_KINGDOM,
    INDIA,
    ITALY,
    JAPAN;

    public static String list(){
        String list = "";
        for (Country country : values()){
            list += country.name() + ", ";
        }
    return list.substring(0, list.length()-2) + ".";
    }
}