package Server;

import Global.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.*;

public class ConnectionHandler implements Runnable {
    private Socket clientSocket;
    private RequestManager requestManager;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


    public ConnectionHandler(Socket clientSocket, RequestManager requestManager) {
        this.clientSocket = clientSocket;
        this.requestManager = requestManager;
    }

    @Override
    public void run() {
        Request userRequest;
        Response responseToUser;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream())) {
            do {
                userRequest = (Request) clientReader.readObject();
                BlockingQueue<Response> queue = new LinkedBlockingQueue<>();
                cachedThreadPool.submit(new HandleRequestTask(userRequest,requestManager,queue));
                responseToUser = queue.take();
                Response finalResponseToUser = responseToUser;
                Runnable sendResponse = () ->{
                    try {
                        clientWriter.writeObject(finalResponseToUser);
                        clientWriter.flush();
                    } catch (IOException e) {
                        System.out.println("Ошибка отправки ответа пользователю!");
                    }
                };
                Thread thread = new Thread(sendResponse);
                thread.start();
                } while (responseToUser.getResponce() != null);
        } catch (NullPointerException e){
            System.out.println("Ошибка: Пустой ответ!");
        } catch (ClassNotFoundException exception) {
            System.out.println("Произошла ошибка при чтении полученных данных!");
        } catch (CancellationException | InterruptedException exception) {
            System.out.println("При обработке запроса произошла ошибка многопоточности!");
        } catch (IOException exception) {
            System.out.println("Непредвиденный разрыв соединения с клиентом!");
        }
    }
}
