package Global.data;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 6372612397367232567L;
    private String user;
    private String password;

    public User(String username, String password) {
        this.user = username;
        this.password = password;
    }

    public String getUsername() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return user + ":" + password;
    }

    @Override
    public int hashCode() {
        return user.hashCode() + password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User) {
            User userObj = (User) obj;
            return user.equals(userObj.getUsername()) && password.equals(userObj.getPassword());
        }
        return false;
    }
}