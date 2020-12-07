public class Square extends MySubject{
    Square(String name){
        super(name);
    }
    public void state(){
        System.out.println(getName()+" оказалась совершенно цела.");
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
