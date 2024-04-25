package pl.sudoku;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    
    private String boardName;
    Connection connection;
    
    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;
        String databaseUrl = "jdbc:derby:D:/Derby/sudokudb;create=true";
        try {
            connection = DriverManager.getConnection(databaseUrl);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }
    }
    
    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        /*try {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); 
        } catch (ClassNotFoundException ex) {
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }*/
        
        try {
            String query = "SELECT ID FROM BOARDS WHERE NAME = ? "
                    + "ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY";
            PreparedStatement prSt = connection.prepareStatement(query);
            prSt.setString(1, boardName);
            ResultSet resSet = prSt.executeQuery();
            resSet.next();
            int id = resSet.getInt(1);
            //System.out.print("id: " + id);
            prSt.close();
            resSet.close();
            String query2 = "SELECT X, Y, FIELDVALUE FROM FIELDS WHERE BOARDID = "
                    + id;
            prSt = connection.prepareStatement(query2);
            ResultSet resSet2 = prSt.executeQuery();
            while (resSet2.next()) {
                sudokuBoard.set(resSet2.getInt("x"), resSet2.getInt("y"), 
                        resSet2.getInt("fieldValue"));
            }
            prSt.close();
            resSet2.close();
            connection.commit();
        } catch (SQLException ex) {
            try { 
                connection.rollback();
            } catch (SQLException e) {
                throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
            }
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }
        return sudokuBoard;
    }
    
    @Override
    public void write(SudokuBoard board) {
        try {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); 
        } catch (ClassNotFoundException ex) {
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet results = dbmd.getTables(null, null, "BOARDS", null);
            if (!results.next()) {
                Statement st = connection.createStatement();
                String sql = "CREATE TABLE BOARDS "
                    + "(ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS "
                    + "AS IDENTITY(Start with 1, Increment by 1),"
                    + "NAME VARCHAR(20) NOT NULL)";
                st.executeUpdate(sql);
                sql = "CREATE TABLE FIELDS "
                    + "(X INTEGER NOT NULL, Y INTEGER NOT NULL, "
                    + "FIELDVALUE INTEGER NOT NULL, "
                    + "BOARDID INTEGER NOT NULL REFERENCES BOARDS(ID), "
                    + "PRIMARY KEY (X, Y, BOARDID))";
                st.executeUpdate(sql);
                st.close();
                results.close();
            }
            String insert1 = "INSERT INTO BOARDS (NAME) VALUES (?)";
            String insert2 = "INSERT INTO FIELDS (X, Y, FIELDVALUE, BOARDID)"
                    + " VALUES (?,?,?,?)";
            String select = "SELECT ID FROM BOARDS WHERE NAME = ? "
                    + "ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY";
            PreparedStatement prSt1 = connection.prepareStatement(insert1);
            prSt1.setString(1, boardName);
            prSt1.executeUpdate();
            prSt1.close();
            PreparedStatement prSt2 = connection.prepareStatement(insert2);
            PreparedStatement prSt3 = connection.prepareStatement(select);
            prSt3.setString(1, boardName);
            ResultSet results2 = prSt3.executeQuery();
            results2.next();
            int id = results2.getInt(1);
            prSt3.close();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    prSt2.setInt(1, i);
                    prSt2.setInt(2, j);
                    prSt2.setInt(3, board.get(i, j));
                    prSt2.setInt(4, id);
                    prSt2.executeUpdate();
                }
            }
            prSt2.close();
            results2.close();
            connection.commit();
        } catch (SQLException ex) {
            try { 
                connection.rollback();
            } catch (SQLException e) {
                throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
            }
            throw new DataBaseException(DataBaseException.MESSAGE_KEY, ex);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
    
    
}
