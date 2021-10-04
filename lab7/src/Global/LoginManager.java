package Global;

import Global.Commands.Clear;
import Global.data.User;

import java.util.Scanner;

public class LoginManager {
    private UserManager userManager;
    private Scanner userScanner;
    public static User user = null;

    public LoginManager(UserManager userManager){
        this.userManager = userManager;
        userScanner = userManager.getUserScanner();
    }

    public Request loginToServer(){
        String command = "";
        System.out.println("Для входа введите \"login\", для регистрации \"register\":");
        String userCommand = userScanner.nextLine();
        if (userCommand.equals("login") || userCommand.equals("register")){
            command = userCommand;
            user = new User(userManager.askUser(), userManager.askPass());
            return new Request(new Clear(command), user);
        } else if (userCommand.equals("exit")){
            System.out.println("Работа клиента завершена.");
            System.exit(0);
            return null;
        } else {
            System.out.println("Неопознанная команда!");
            return loginToServer();
        }
    }

}
