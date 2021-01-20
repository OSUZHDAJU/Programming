public class Place {
    private String name;
    Place(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static Destiny getDestiny(boolean boo){return new Place.Destiny(boo);}



    public static class Destiny{
        private boolean destiny;


        public Destiny(boolean boo){
            destiny = boo;
        }

        public void setname(String str){
            Place kozlik = new Place("");
            kozlik.name = str;
        }

        public String checkpoint(){
            if (destiny=true){
                return "двор, который оказался проходным.";
            }
            else {
                return "двор, который оказался закрытым.";
            }
        }
    }


}
