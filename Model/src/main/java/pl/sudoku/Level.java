package pl.sudoku;

public enum Level {
    
    HARD(50), MEDIUM(30), EASY(20);
    private final int emptyFields;
    Level(int amountOfFieldsNotFill) {
        this.emptyFields = amountOfFieldsNotFill;
    }

    public void removeFields(final SudokuBoard sudokuBoard) {
        for (int i = 0; i < emptyFields; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 9);
            if (sudokuBoard.get(x, y) != 0) {
                sudokuBoard.set(x, y, 0);
            } else {
                i--;
            }
        }
    }
}
