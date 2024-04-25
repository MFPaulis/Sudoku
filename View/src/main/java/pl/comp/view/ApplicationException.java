
package pl.comp.view;

import java.util.Locale;
import java.util.ResourceBundle;


public class ApplicationException extends Exception {

    public static final String MESSAGE_KEY = "applicationException";
    
    public ApplicationException(String message) {
        super(message);
    }
    
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String getLocalizedMessage() {
        return  ResourceBundle.getBundle("pl.pk.App", Locale.getDefault())
                .getString(getMessage());
    }
}
