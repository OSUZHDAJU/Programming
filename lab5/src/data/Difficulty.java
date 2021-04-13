package data;

public enum Difficulty {
    VERY_HARD,
    INSANE,
    HOPELESS;

    public static String list(){
        String list = "";
        for (Difficulty difficulty : values()){
            list += difficulty.name() + ", ";
        }
        return list.substring(0, list.length()-2) + ".";
    }
}