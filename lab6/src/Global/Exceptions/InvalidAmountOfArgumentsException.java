package Global.Exceptions;

/**
*Выкидывается, если недопустимое количество аргументов.
 */
public class InvalidAmountOfArgumentsException extends Exception{
    public InvalidAmountOfArgumentsException(){
        System.out.println("Недопустимое количество аргументов!");
    }
}
