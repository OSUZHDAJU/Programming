package Global;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -328567015L;
    private String responce;

    public Response(String responce){
        this.responce = responce;
    }

    public String getResponce(){
        return this.responce;
    }

    public boolean isEmpty(){
        return responce == null;
    }
}
