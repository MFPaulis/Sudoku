
package pl.sudoku;

import java.util.Locale;
import java.util.ResourceBundle;

public class ModelException extends RuntimeException {

    public static final String MESSAGE_KEY = "modelException";
    
    public ModelException(String message) {
        super(message);
    }
    
    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getLocalizedMessage() {
        return  ResourceBundle.getBundle("pl.pk.App", Locale.getDefault())
                .getString(getMessage());
    }
}
