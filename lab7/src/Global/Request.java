package Global;

import Global.Commands.Command;
import Global.data.User;

import java.io.Serializable;

public class Request implements Serializable {
    private Command command;
    private User user;

    public Request(Command command, User user){
        this.command = command;
        this.user = user;
    }

    public Command getCommand(){
        return this.command;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEmpty(){
        return command == null;
    }
}
