package data;

import java.util.Objects;

/**
 * Тип, описывающий местоположение автора.
 */
public class Location {
    private long x;
    private Double y; //Поле не может быть null
    private Float z; //Поле не может быть null

    public Location(long x, Double y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y.equals(location.y) && z.equals(location.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}