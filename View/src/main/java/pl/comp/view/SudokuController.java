package pl.comp.view;



import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sudoku.Dao;
import pl.sudoku.SudokuBoard;
import pl.sudoku.SudokuBoardDaoFactory;


public class SudokuController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(SudokuController.class);
    private Game game;
    private JavaBeanIntegerProperty[][] properties = new JavaBeanIntegerProperty[9][9];
    @FXML private GridPane sudokuBoardGrid;
    @FXML private Button saveButton;
    @FXML private Button loadButton;
    @FXML private ResourceBundle resources;
    @FXML private TextField boardName;
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resources = rb;
        try {
            initializeSudokuGrid();
        } catch (NoSuchMethodException ex) {
            System.out.print(ex);
        }
    }    
    
     private void initializeSudokuGrid() throws NoSuchMethodException {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                GridPane grid = new GridPane();
                GridPane.setConstraints(grid, y, x);
                sudokuBoardGrid.getChildren().add(grid);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int comX = x * 3 + i;
                        int comY = y * 3 + j;
                        Field field = new Field(comX, comY, 
                                Integer.toString(this.game.getSudokuBoard()
                                        .get(comX, comY)));
                        GridPane.setConstraints(field, j, i);
                        grid.getChildren().add(field);
                        
                        properties[comX][comY] = new JavaBeanIntegerPropertyBuilder()
                                .bean(game.getSudokuBoard().getField(comX, comY))
                                .name("fieldValue").build();
                        Bindings.bindBidirectional(field.textProperty(), 
                                properties[comX][comY], new NumberStringConverter());
                        /*
                        Bindings.bindBidirectional(field.valueProperty, 
                                JavaBeanIntegerPropertyBuilder.create()
                                .bean(game.getSudokuBoard().getField(computedX, computedY))
                                .name("fieldValue").build(), new NumberStringConverter());
                        *//*
                        field.valueProperty = new JavaBeanIntegerPropertyBuilder()
                                .bean(game.getSudokuBoard().getField(comX, comY))
                                .name("fieldValue").build();
                        Bindings.bindBidirectional(field.textProperty(), 
                                field.valueProperty, new NumberStringConverter());*/
                        /*field.textProperty().bindBidirectional(field.valueProperty, 
                                new NumberStringConverter());*/
                        
                        /*
                        field.valueProperty.addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable, 
                                    Number oldValue, Number newValue) {
                                System.out.println("Changed");
                                game.getSudokuBoard().set(computedX, computedY, 
                                        newValue.intValue());
                            }
                        });*/
                        properties[comX][comY].addListener(new ChangeListener<Number>() {
                            @Override
                            public void changed(ObservableValue<? extends Number> observable, 
                                    Number oldValue, Number newValue) {
                                //System.out.println("Changed");
                                logger.info("Property changed");
                            }
                        });
                        field.textProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, 
                                    String oldValue, String newValue) {
                                //System.out.println("Changed textProperty");
                                logger.info("Text property changed");
                            }
                        });
                    }
                }
            }
        }
    }
     
    public void save(ActionEvent event) throws ApplicationException {
        /*SudokuBoard board = game.getSudokuBoard();
        try (Dao dao = SudokuBoardDaoFactory.getFileDao("file")) {
            dao.write(board);
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationException.MESSAGE_KEY, ex);
        }*/
        TextInputDialog textInput = new TextInputDialog();
        String text = ResourceBundle.getBundle("pl.pk.App", Locale.getDefault())
                .getString("wybierzPlansze");
        String text2 = ResourceBundle.getBundle("pl.pk.App", Locale.getDefault())
        .getString("zapisz");
        textInput.getDialogPane().setContentText(text);
        textInput.setTitle(text2);
        textInput.setHeaderText(null);
        textInput.setGraphic(null);
        textInput.showAndWait();
        TextField input = textInput.getEditor();
        if (input != null && input.getText().toString().length() != 0) {
            SudokuBoard board = game.getSudokuBoard();
                try (Dao dao = SudokuBoardDaoFactory.getJdbcDao(input.getText())) {
                    dao.write(board);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new ApplicationException(ApplicationException.MESSAGE_KEY, ex);
                }
        }
    }
    
    public void load(ActionEvent event) throws ApplicationException {
        /*String s = "\n";
        SudokuBoard board = game.getSudokuBoard();
        try (Dao dao = SudokuBoardDaoFactory.getFileDao("file")) {
            SudokuBoard board2 = (SudokuBoard) dao.read();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board.set(i, j, board2.get(i, j));
                    //System.out.print(game.getSudokuBoard().get(i, j));
                    s += Integer.toString(game.getSudokuBoard().get(i, j));
                }
                s += "\n";
                //System.out.println();
            }
            logger.debug(s);
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationException.MESSAGE_KEY, ex);
        }*/
        TextInputDialog textInput = new TextInputDialog();
        String text = ResourceBundle.getBundle("pl.pk.App", Locale.getDefault())
                .getString("wybierzPlansze");
        String text2 = ResourceBundle.getBundle("pl.pk.App", Locale.getDefault())
        .getString("wczytaj");
        textInput.getDialogPane().setContentText(text);
        textInput.setTitle(text2);
        textInput.setHeaderText(null);
        textInput.setGraphic(null);
        textInput.showAndWait();
        TextField input = textInput.getEditor();
        if (input != null && input.getText().toString().length() != 0) {
            String s = "\n";
            SudokuBoard board = game.getSudokuBoard();
            try (Dao dao = SudokuBoardDaoFactory.getJdbcDao(input.getText())) {
                SudokuBoard board2 = (SudokuBoard) dao.read();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        board.set(i, j, board2.get(i, j));
                        s += Integer.toString(game.getSudokuBoard().get(i, j));
                    }
                    s += "\n";
                }
                logger.debug(s);
            } catch (Exception ex) {
                throw new ApplicationException(ApplicationException.MESSAGE_KEY, ex);
            }
        }
    }
}
