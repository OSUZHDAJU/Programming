package Global.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Главный тип объекта, хранящегося в коллекции.
 */
public class LabWork implements Comparable<LabWork>, Serializable {
    private static final long serialVersionUID = 6372612397367232540L;
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private Integer tunedInWorks;
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null

    public LabWork(Long id, String name, Coordinates coordinates, LocalDateTime creationDate,
                   Long minimalPoint, String description, Integer tunedInWorks, Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.tunedInWorks = tunedInWorks;
        this.difficulty = difficulty;
        this.author = author;
    }

    public void setId(Long id ){this.id = id;}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getMinimalPoint() {
        return minimalPoint;
    }

    public String getDescription() {
        return description;
    }

    public int getTunedInWorks() {
        return tunedInWorks;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", description='" + description + '\'' +
                ", tunedInWorks=" + tunedInWorks +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return id == labWork.id && tunedInWorks == labWork.tunedInWorks && name.equals(labWork.name) &&
                coordinates.equals(labWork.coordinates) && creationDate.equals(labWork.creationDate) &&
                minimalPoint.equals(labWork.minimalPoint) && description.equals(labWork.description) &&
                difficulty == labWork.difficulty && author.equals(labWork.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, minimalPoint, description, tunedInWorks, difficulty, author);
    }

    @Override
    public int compareTo(LabWork o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) result = this.minimalPoint.compareTo(o.minimalPoint);
        return result;
    }
}


