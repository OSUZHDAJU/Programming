
public class Seller {
    private String state = "here";
    private String name;
    public static Seller seller;

    Seller(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static String getRod(){
        return "продавщиц";
    }


   public void say() throws SellerExeption {
        if(state == "here")
            System.out.println(getName()+" сказала, что "+Shop.getshortName()+" закрылся, так как хозяин неожиданно разбогател и уехал путешествовать, а теперь здесь открылась кондитерская.");
        else
            throw new SellerExeption("Продавщицы не было на месте.");
    }
}
