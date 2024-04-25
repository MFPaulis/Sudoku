
package pl.sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    
    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private FileOutputStream fileOut;
    private ObjectOutputStream out;
    private FileInputStream fileIn;
    private ObjectInputStream in;
    //private String fileName;
            
    public FileSudokuBoardDao(String fileName) { //throws IOException {
        //this.fileName = fileName;
        try {
            fileIn = new FileInputStream(fileName);
            in = new ObjectInputStream(fileIn);
            fileOut = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fileOut);
        } catch (IOException ex) {
            logger.error("Wczytywanie nie udalo sie");
            throw new ModelException(ModelException.MESSAGE_KEY);
        }
    }
    
    @Override
    public SudokuBoard read() {
        try {
            //fileIn = new FileInputStream(fileName);
            //in = new ObjectInputStream(fileIn);
            SudokuBoard sudokuBoard = (SudokuBoard)in.readObject();
            return sudokuBoard;
            
        }  catch (IOException | ClassNotFoundException ex) {
            logger.error("Wczytywanie nie udalo sie");
            throw new ReadException(ReadException.MESSAGE_KEY);
        }
    }
    
    @Override
    public void write(SudokuBoard obj) {
        try {
            //fileOut = new FileOutputStream(fileName);
            //out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
              
        } catch (IOException e) {
            logger.error("Nie znaleziono pliku");
            throw new WriteException(WriteException.MESSAGE_KEY);
        } 
    }
    
    @Override
    public void close() {
        try {
            logger.info("Zamykam plik");
            if (fileOut != null) {
                fileOut.close();
                out.close();
            }
            if (fileIn != null) {
                fileIn.close();
                in.close();
            }
        } catch (IOException e) {
            logger.error("Nie udalo sie zamknac pliku");
            throw new CloseException(CloseException.MESSAGE_KEY);
        }
    }
}