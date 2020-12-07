public enum Status {
    NOTSTAY(" ни стоящей,"),
    NOTLIE(" ни лежащей,"),
    NOTWHOLE(" ни целой,"),
    NOTBRAKE(" ни сломанной.");

    private String status;

    Status(String str){
        status = str;
    }

    public String getStatus(){
        return status;
    }
}
