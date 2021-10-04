package Server;

import Global.Exceptions.*;

import java.io.IOException;
import java.net.*;

import java.util.concurrent.*;

public class Server {
    private final int port;
    private ServerSocket serverSocket;
    private final RequestManager requestManager;
    private boolean isStopped;
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private final ServerInput serverInput = new ServerInput(this);

    public Server(int port, RequestManager requestManager) {
        this.port = port;
        this.requestManager = requestManager;
    }

    public void run() {
        try {
            openServerSocket();
            serverInput.start();
            while (!isStopped()) {
                try {
                    if (isStopped()) throw new ConnectionErrorException();
                    Socket clientSocket = connectToClient();
                    cachedThreadPool.submit(new ConnectionHandler(clientSocket, requestManager ));
                } catch (ConnectionErrorException exception) {
                    if (!isStopped()) {
                        System.out.println("Произошла ошибка при соединении с клиентом!");
                    } else break;
                }
            }
            cachedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("Работа сервера завершена.");
        } catch (OpeningServerSocketException exception) {
            System.out.println("Сервер не может быть запущен!");
        } catch (InterruptedException e) {
            System.out.println("Произошла ошибка при завершении работы с уже подключенными клиентами!");
        }
    }

    public synchronized void stop() {
        try {
            System.out.println("Завершение работы сервера...");
            if (serverSocket == null) throw new ClosingSocketException();
            isStopped = true;
            cachedThreadPool.shutdown();
            serverSocket.close();
            requestManager.getCollectionManager().getDbCollectionManager().getDbInteraction().closeConnection();
            System.out.println("Завершение работы с уже подключенными клиентами...");
            System.out.println("Работа сервера завершена.");
            System.exit(0);
        } catch (ClosingSocketException exception) {
            System.out.println("Невозможно завершить работу еще не запущенного сервера!");
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при завершении работы сервера!");
        }
    }

    private synchronized boolean isStopped() {
        return isStopped;
    }

    private void openServerSocket() throws OpeningServerSocketException {
        try {
            System.out.println("Запуск сервера...");
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Порт '" + port + "' находится за пределами возможных значений!");
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при попытке использовать порт '" + port + "'!");
        }
    }

    private Socket connectToClient() throws ConnectionErrorException {
        try {
            System.out.println("Прослушивание порта '" + port + "'...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Соединение с клиентом установлено.");
            return clientSocket;
        } catch (IOException exception) {
            throw new ConnectionErrorException();
        }
    }
}
