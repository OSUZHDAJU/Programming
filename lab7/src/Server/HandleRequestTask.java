package Server;

import Global.Request;
import Global.Response;
import Global.data.User;

import java.util.concurrent.BlockingQueue;

public class HandleRequestTask implements Runnable {
    private Request request;
    private RequestManager requestManager;
    private final BlockingQueue<Response> queue;

    public HandleRequestTask(Request request, RequestManager requestManager,BlockingQueue<Response> queue){
        this.request = request;
        this.requestManager = requestManager;
        this.queue = queue;
    }

    @Override
    public void run(){
        User hashedUser = new User(
                request.getUser().getUsername(),
                PasswordHasher.hashPassword(request.getUser().getPassword())
        );
        request.setUser(hashedUser);
        requestManager.getCollectionManager().addToHistory(request.getCommand().toString());
        Response response = requestManager.process(request);
        try {
            queue.put(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
