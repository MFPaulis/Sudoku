
package pl.sudoku;


public class CloseException extends DaoException {

    public static final String MESSAGE_KEY = "closeException";
    
    public CloseException(String message) {
        super(message);
    }
    
    public CloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
