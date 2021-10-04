package Global.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Тип описывающий местоположение главного объекта.
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 6372612397367232541L;
    private long x;
    private float y; //Максимальное значение поля: 640

    public Coordinates(long x, float y) {
        this.x = x;
        this.y = y;
    }

    public long getX() { return x; }

    public float getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}