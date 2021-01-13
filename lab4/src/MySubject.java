import java.util.Objects;

public abstract class MySubject {
    String name;
    public MySubject(String name){
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MySubject mySubject = (MySubject) obj;
        return Objects.equals(name, mySubject.name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

