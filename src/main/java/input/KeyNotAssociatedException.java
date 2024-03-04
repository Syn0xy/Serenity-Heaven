package input;

public class KeyNotAssociatedException extends RuntimeException {

    public KeyNotAssociatedException(String msg){
        super(msg);
    }
    
    public KeyNotAssociatedException(){
        super();
    }

}
