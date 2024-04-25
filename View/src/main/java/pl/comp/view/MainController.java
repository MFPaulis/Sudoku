
package pl.comp.view;

import static pl.sudoku.Level.EASY;
import static pl.sudoku.Level.HARD;
import static pl.sudoku.Level.MEDIUM;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.sudoku.Level;

public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private ComboBox comboBox;
    @FXML private Button button;
    @FXML private ResourceBundle resources;
    @FXML private Text authors;
    @FXML private RadioButton rbutton1;
    @FXML private RadioButton rbutton2;
    @FXML private Text difficultyText;
    private int value;
    @FXML private Locale locale;
    
    public void comboBoxUpdated() {
        value = comboBox.getSelectionModel().getSelectedIndex();
    }
    
    public void buttonClicked(ActionEvent event) throws LoadingException {
        if (value == 0) {
            runGame(EASY, event);
        } else if (value == 1) {
            runGame(MEDIUM, event);
        } else {
            runGame(HARD, event);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        this.resources = resources;
        comboBox.getItems().addAll(resources.getString("poziomLatwy"), 
                resources.getString("poziomSredni"), 
                resources.getString("poziomTrudny"));
        value = 0;
        rbutton1.setSelected(resources.getLocale().getLanguage() == "pl");
        rbutton2.setSelected(resources.getLocale().getLanguage() == "en");
        ResourceBundle rb  = ResourceBundle.getBundle("pl.comp.view.Author");
        authors.setText(rb.getString("229995") + "\n" + rb.getString("236690"));

    }
    
    private void runGame(final Level level, ActionEvent event) throws LoadingException {
        Game game = new Game(level);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("fxml/Sudoku.fxml"), resources);
        SudokuController controller = new SudokuController();
        controller.setGame(game);
        loader.setController(controller);
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new LoadingException(LoadingException.MESSAGE_KEY, ex);
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void radioSelect(ActionEvent event) throws LoadingException {
        if (rbutton1.isSelected()) {
            loadLang("pl");
        } 
        if (rbutton2.isSelected()) {
            loadLang("en");
        }
        
    }
    
    private void loadLang(String language) throws LoadingException {
        locale = new Locale(language);
        resources = ResourceBundle.getBundle("pl.pk.App", locale);
        stage = (Stage) rbutton1.getScene().getWindow();
        /*
        rbutton1.setText(resources.getString("jezykPolski"));
        rbutton2.setText(resources.getString("jezykAngielski"));
        difficultyText.setText(resources.getString("wybierzPoziomTrudnosci"));
        ObservableList<String> data = FXCollections.observableArrayList(
                resources.getString("poziomLatwy"), resources.getString("poziomSredni"), 
                resources.getString("poziomTrudny"));
        comboBox.setItems(data);
        */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader()
                .getResource("fxml/Main.fxml"), resources); 
        try {
            Parent root = fxmlLoader.load();
            stage.getScene().setRoot(root);
            Locale.setDefault(locale);
        } catch (IOException ex) {
            throw new LoadingException(LoadingException.MESSAGE_KEY, ex);
        }
        
    }
    
}
