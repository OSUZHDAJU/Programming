package Server;


import Global.Request;
import Global.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Scanner;

public class Server {
    private int port;
    private RequestManager requestManager;
    private SocketAddress address;
    private SocketAddress clientAddress;
    private DatagramChannel channel;
    private Scanner scanner;
    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(16384);
    private CollectionManager collectionManager;

    public Server(int port, RequestManager requestManager) throws IOException{
        this.port = port;
        this.requestManager = requestManager;
        collectionManager = requestManager.getCollectionManager();
        channel = DatagramChannel.open();
        address = new InetSocketAddress(port);
        channel.socket().bind(address);
        selector = Selector.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }

    public void run() {
        System.out.println("Сервер запущен!");
        boolean statusServer = true;
        scanner = new Scanner(System.in);
        Runnable userInput = () -> {
            try {
                while (true) {
                    if (!scanner.hasNext()) continue;
                    String input = scanner.nextLine();
                    if (input.equals("exit")){
                        collectionManager.save();
                        System.out.println("Завершение работы сервера.");
                        System.exit(0);
                    } else if (input.equals("save")){
                        collectionManager.save();
                    } else {
                        System.out.println("Неверная команда!");
                    }
                }
            } catch (Exception ignored){}
        };
        Thread thread = new Thread(userInput);
        thread.start();
        while (statusServer){
            statusServer = exchangeDataWithClient();
        }
    }

    private void connect(){

    }

    private boolean exchangeDataWithClient(){
        Request request = null;
        Response response = null;
        connect();
        try{
            do{
                request = receive();
                if (request == null) continue;
                System.out.println("Получена команда: "+request.getCommand());
                response = requestManager.process(request);
                System.out.println("Команда "+request.getCommand()+" выполнена.");
                send(response);
            } while (request.isEmpty());
        } catch (Exception ignored){}
        return true;
    }

    private Request receive(){
        try{
            byteBuffer.clear();
            clientAddress = channel.receive(byteBuffer);
            byteBuffer.flip();
            channel.register(selector, SelectionKey.OP_WRITE);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectOutputStream = new ObjectInputStream(byteArrayInputStream);
            Request request = (Request) objectOutputStream.readObject();
            byteArrayInputStream.close();
            objectOutputStream.close();
            byteBuffer.clear();
            return request;
        } catch (ClassNotFoundException | IOException e){
            return null;
        }
    }


    private void send(Response response){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            byte[] buffer = byteArrayOutputStream.toByteArray();
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            byteBuffer.clear();
            byteBuffer.put(buffer);
            byteBuffer.flip();
            channel.send(byteBuffer, clientAddress);
            channel.register(selector, SelectionKey.OP_READ);
            byteBuffer.clear();
            System.out.println("Пакет отправлен.");
            System.out.println();
        } catch (IOException e){
            System.out.println("Ошибка отправки ответа!");
        }

    }
}

