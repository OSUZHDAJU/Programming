package Server;

import Global.*;

public class ServerMain {
    public static final int port = 3879;
    private static String databaseUsername = "postgres";
    private static String databaseAddress = "jdbc:postgresql://localhost:5432/studs";
    private static String databasePassword = "1234";

    public static void main(String[] args) {
        DBInteraction dbInteraction = new DBInteraction(databaseAddress,databaseUsername,databasePassword);
        DBUserManager dbUserManager = new DBUserManager(dbInteraction);
        DBCollectionManager dbCollectionManager = new DBCollectionManager(dbInteraction,dbUserManager);
        CollectionManager collectionManager = new CollectionManager(dbCollectionManager);
        RequestManager requestManager = new RequestManager(collectionManager);
        Server server = new Server(port,requestManager);
        server.run();
    }
}
