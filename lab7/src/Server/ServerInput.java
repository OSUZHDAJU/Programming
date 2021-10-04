package Server;

import java.util.Scanner;

public class ServerInput extends Thread{
    private Server server;

    public ServerInput(Server server){
        this.server = server;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        do{
            if(!scanner.hasNext()) continue;
            String input = scanner.nextLine();
            if (input.equals("exit")){
                server.stop();
            }
            else {
                System.out.println("Неверная команда.");
            }
        } while (scanner.hasNext());
    }
}
