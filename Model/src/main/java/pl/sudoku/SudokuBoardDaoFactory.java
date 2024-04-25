
package pl.sudoku;


public class SudokuBoardDaoFactory {
    
    private SudokuBoardDaoFactory() {
    }
    
    public static Dao getFileDao(String fileName) { //throws IOException {
        return new FileSudokuBoardDao(fileName);
    }
    
    public static Dao getJdbcDao(String boardName) { //throws IOException {
        return new JdbcSudokuBoardDao(boardName);
    }
}
