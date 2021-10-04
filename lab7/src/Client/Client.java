package Client;

import Global.*;
import Global.Exceptions.ConnectionErrorException;
import Global.data.User;


import java.io.*;
import java.net.*;


public class Client {
    private String host;
    private int port;
    private Socket socket;
    private LoginManager loginManager;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    public User user;
    private Integer reconnectionTimeout = 6000;
    private CommandManager commandManager;

    public Client(String host, int port,LoginManager loginManager, CommandManager commandManager) {
        this.host = host;
        this.port = port;
        this.loginManager = loginManager;
        this.commandManager = commandManager;
    }

    public void run() {
        try {
            while (true) {
                try {
                    connectToServer();
                    processAuthentication();
                    processRequestToServer();
                    break;
                } catch (ConnectionErrorException exception) {
                    try {
                        Thread.sleep(reconnectionTimeout);
                    } catch (IllegalArgumentException timeoutException) {
                        System.out.println("Время ожидания подключения '" + reconnectionTimeout +
                                "' находится за пределами возможных значений!");
                        System.out.println("Повторное подключение будет произведено немедленно.");
                    } catch (Exception timeoutException) {
                        System.out.println("Произошла ошибка при попытке ожидания подключения!");
                        System.out.println("Повторное подключение будет произведено немедленно.");
                    }
                }
            }
            if (socket!= null) socket.close();
            System.out.println("Работа клиента завершена.");
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при попытке завершить соединение с сервером!");
        }
    }

    private void connectToServer() throws ConnectionErrorException{
        try {
            socket = new Socket(host,port);
            System.out.println("Соединение с сервером установлено.");
            System.out.println("Ожидание разрешения на обмен данными...");
            serverWriter = new ObjectOutputStream(socket.getOutputStream());
            serverReader = new ObjectInputStream(socket.getInputStream());
            System.out.println("Разрешение на обмен данными получено.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Адрес сервера введен некорректно!");
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при соединении с сервером!");
        }
    }

    private void processRequestToServer() {
        Request requestToServer = null;
        Response serverResponse;
        do {
            try {
                requestToServer = commandManager.generateRequest(commandManager.readCommand(), user);
                if (requestToServer.getCommand() == null) processRequestToServer();
                serverWriter.writeObject(requestToServer);
                serverWriter.flush();
                serverResponse = (Response) serverReader.readObject();
                System.out.println(serverResponse.getResponce());
            } catch (InvalidClassException exception) {
                System.out.println("Произошла ошибка при отправке данных на сервер!");
            } catch (NotSerializableException exception){
                exception.printStackTrace();
            } catch (ClassNotFoundException exception) {
                System.out.println("Произошла ошибка при чтении полученных данных!");
            } catch (IOException exception) {
                System.out.println("Соединение с сервером разорвано!");
                try {
                    connectToServer();
                } catch (ConnectionErrorException reconnectionException) {
                    if (requestToServer.getCommand().equals("exit"))
                        System.out.println("Команда не будет зарегистрирована на сервере.");
                    else System.out.println("Попробуйте повторить команду позднее.");
                }
            }
        } while (!requestToServer.getCommand().equals("exit"));
    }

    private void processAuthentication() {
        Request requestToServer = null;
        Response serverResponse = null;
        do {
            try {
                requestToServer = loginManager.loginToServer();
                if (requestToServer.isEmpty()) continue;
                serverWriter.writeObject(requestToServer);
                serverWriter.flush();
                serverResponse = (Response) serverReader.readObject();
                System.out.println(serverResponse.getResponce());
            } catch (InvalidClassException | NotSerializableException exception) {
                System.out.println("Произошла ошибка при отправке данных на сервер!");
            } catch (ClassNotFoundException exception) {
                System.out.println("Произошла ошибка при чтении полученных данных!");
            } catch (IOException exception) {
                System.out.println("Соединение с сервером разорвано!");
                exception.printStackTrace();
                try {
                    connectToServer();
                } catch (ConnectionErrorException reconnectionException) {
                    System.out.println("Попробуйте повторить авторизацию позднее.");
                }
            }
        } while (serverResponse.getResponce().equals("Неправильные имя пользователя или пароль!"));
        user = requestToServer.getUser();
    }
}
