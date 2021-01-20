
public class Shop extends MySubject {
    public static Shop shop;
    public Shop(String name) {
        super(name);
        this.name = name;
    }

    static String getName() {
        return "магазин разнокалиберных товаров";
    }

    static String getshortName() {
        return "разнокалиберный магазин";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
