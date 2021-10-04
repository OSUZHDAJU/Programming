package Global.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Тип описывающий автора.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 6372612397367232547L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; //Поле не может быть null
    private EyeColor eyeColor; //Поле может быть null
    private HairColor hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null


    public Person(String name, LocalDateTime birthday, EyeColor eyeColor, HairColor hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && birthday.equals(person.birthday) && eyeColor == person.eyeColor && hairColor == person.hairColor && nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, hairColor, nationality);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }


}