
package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
   
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Locale polish = new Locale.Builder().setLanguage("pl").build();
            //Locale english = new Locale.Builder().setLanguage("en").build();
            ResourceBundle bundle = ResourceBundle.getBundle("pl.pk.App", polish);
            Parent root = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("fxml/Main.fxml"), bundle);
            Scene scene = new Scene(root, 600, 500);
            
            primaryStage.setTitle("Sudoku Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
