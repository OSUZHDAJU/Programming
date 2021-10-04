package Server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            BigInteger integer = new BigInteger(1, bytes);
            String newPassword = integer.toString(16);
            while (newPassword.length() < 32){
                newPassword = "0" + newPassword;
            }
            return newPassword;
        }catch (NoSuchAlgorithmException e){
            System.out.println("Не найден алгоритм хэширования пароля!");
            throw new IllegalStateException(e);
        }
    }

}
