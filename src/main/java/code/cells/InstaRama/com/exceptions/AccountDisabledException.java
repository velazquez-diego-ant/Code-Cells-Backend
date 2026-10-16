package code.cells.InstaRama.com.exceptions;

public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(String message){
        super(message);
    }
}
