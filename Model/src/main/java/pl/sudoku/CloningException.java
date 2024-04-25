
package pl.sudoku;

public class CloningException extends ModelException {

    public static final String MESSAGE_KEY = "cloningException";
    
    public CloningException(String message) {
        super(message);
    }
    
    public CloningException(String message, Throwable cause) {
        super(message, cause);
    }
}
