package Server;

import java.io.IOException;

public class ServerMain {
    public static final int port = 3356;

    public static void main(String[] args) throws IOException {
        CollectionManager collectionManager = new CollectionManager();
        RequestManager requestManager = new RequestManager(collectionManager);
        collectionManager.read();
        Server server = new Server(port,requestManager);
        server.run();
    }
}
