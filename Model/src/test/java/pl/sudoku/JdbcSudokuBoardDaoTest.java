/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paulina
 */
public class JdbcSudokuBoardDaoTest {
    
    public JdbcSudokuBoardDaoTest() {
    }

    @Test
    public void testReadAndWrite() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        try (Dao dao = SudokuBoardDaoFactory.getJdbcDao("test")) {
            dao.write(board);
            SudokuBoard board2 = (SudokuBoard) dao.read();
            assertNotSame(board, board2);
            assertEquals(board, board2);
        } catch (Exception ex) {
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }
    }
    
    @Test
    public void testDataBaseException() {
        try (Dao dao = SudokuBoardDaoFactory.getJdbcDao("nieistnieje")) {
            assertThrows(DataBaseException.class, () -> {
                dao.read();
            });
        } catch (Exception ex) {
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }
    }
    
}
