package data;

public enum EyeColor {
    BLUE,
    YELLOW,
    ORANGE;

    public static String list(){
        String list = "";
        for (EyeColor eyeColor : values()){
            list += eyeColor.name() + ", ";
        }
        return list.substring(0, list.length()-2) + ".";
    }
}