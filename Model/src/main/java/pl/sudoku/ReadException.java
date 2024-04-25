
package pl.sudoku;


public class ReadException extends DaoException {
    
    public static final String MESSAGE_KEY = "readException";
    
    public ReadException(String message) {
        super(message);
    }
    
    public ReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
