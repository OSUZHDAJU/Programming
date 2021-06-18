package Global.Exceptions;

public class UniqueException extends Exception {
    private String message;
    public UniqueException(String message){
        System.out.println(message);
        this.message = message;
    }


}
