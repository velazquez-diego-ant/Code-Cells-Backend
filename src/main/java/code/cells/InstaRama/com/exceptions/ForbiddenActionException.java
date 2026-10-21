package code.cells.InstaRama.com.exceptions;

public class ForbiddenActionException extends RuntimeException {
    public ForbiddenActionException(String message){
        super(message);
    }
}
