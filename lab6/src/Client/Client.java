package Client;

import Global.Request;
import Global.Response;
import Global.UserManager;


import java.io.*;
import java.net.*;
import java.sql.SQLOutput;


public class Client {
    private String host;
    private int port;
    private DatagramSocket socket;
    private InetAddress address;
    private CommandManager commandManager;
    private UserManager userManager;

    public Client(String host, int port, CommandManager commandManager, UserManager userManager) {
        this.host = host;
        this.port = port;
        this.commandManager = commandManager;
        this.userManager = userManager;
    }

    public void start(){
        System.out.println("Клиент запущен.");
        try{
            boolean running = true;
            while (running){
                socket = new DatagramSocket();
                address = InetAddress.getByName(host);
                running = exchangeWithServer();
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Проверьте правильность введенного адреса.");
        } catch (IOException e) {
            System.out.println("Ошибка при соединении с сервером.");
        }
    }

    public boolean exchangeWithServer(){
        Request request = null;
        Response response = null;
        do{
            try{
                String[] commandWithArgument = commandManager.readCommand();
                request = commandManager.generateRequest(commandWithArgument);
                if (request == null) exchangeWithServer();
                sendData(request);
                response = recieveData();
                System.out.println(response.getResponce());
            } catch (ClassNotFoundException | IOException e){
                System.out.println("Произошла ошибка при работе с сервером!");
            } catch (NullPointerException e){}
        } while (!response.isEmpty());
        return false;
    }

    private byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

    private Response deserialize(DatagramPacket getPacket) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }

    public void sendData(Request request) throws IOException {
        byte[] data = serialize(request);
        DatagramPacket sendPacket = new DatagramPacket(data,data.length,address,port);
        socket.send(sendPacket);
    }

    public Global.Response recieveData() throws IOException, ClassNotFoundException {
        byte[] data = new byte[socket.getReceiveBufferSize()];
        DatagramPacket getPacket = new DatagramPacket(data,data.length);
        socket.receive(getPacket);
        return deserialize(getPacket);
    }
}
