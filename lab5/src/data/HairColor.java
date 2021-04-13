package data;

public enum HairColor {
    BLACK,
    BLUE,
    YELLOW,
    ORANGE;

    public static String list(){
        String list = "";
        for (HairColor hairColor : values()){
            list += hairColor.name() + ", ";
        }
        return list.substring(0, list.length()-2) + ".";
    }
}