
package pl.sudoku;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileSudokuBoardDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDaoTest.class);
    
    @Test
    public void testReadAndWrite() throws Exception {
        logger.info("read and write");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        try (Dao dao = SudokuBoardDaoFactory.getFileDao("file")) {
            dao.write(board);
            SudokuBoard board2 = (SudokuBoard) dao.read();
            assertEquals(board, board2);
        }
        
    }
    
    @Test
    public void testFileNotFoundRead() {
        Exception exception = Assertions.assertThrows(ModelException.class, () -> {
           try (Dao dao = SudokuBoardDaoFactory.getFileDao("file1")) {
                dao.read();
            }
         });
        Locale locale = new Locale("pl");
        Locale.setDefault(locale);
        Assertions.assertEquals("Błąd w aplikacji", exception.getLocalizedMessage());   
    }
    /*
    @Test
    public void testFileNotFoundWrite() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        Exception exception = Assertions.assertThrows(WriteException.class, () -> {
           try (Dao dao = SudokuBoardDaoFactory.getFileDao("*")) {
                dao.write(board);
            }
         });
        Assertions.assertEquals("Zapis do pliku nie udał się", exception.getLocalizedMessage());   
    }*/
   
}
