package Global.Commands;


import Global.Exceptions.IncorrectInputException;
import Global.Request;
import Global.data.User;

import java.io.Serializable;
import java.util.Objects;

/**
 * Абстрактный класс для всех команд.
 */
public abstract class Command implements Serializable {
    private String name;
    private String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Request execute(String[] arg, User user) throws IncorrectInputException {
        return getRequest(user);
    }

    public Request getRequest(User user){
        Request req = new Request(this, user);
        return req;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return name.equals(command.name) && description.equals(command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return name + " - " + description + ".";
    }
}
