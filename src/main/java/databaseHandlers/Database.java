package databaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 Class Database is a singleton
 This ensures that there is only ever 1 connection to the database and that this connection is open
 */
public class Database {

    // Used to check if there is already a Database object made
    private static Database instance = null;
    private static Connection conn;

    private Database() throws SQLException{
        conn = connect();
    }

    /*
     execute(String statement)

     @param statement: String, the SQL statement to be executed
     @return void
     */
    public void execute(String statement) throws SQLException{
        conn.createStatement().executeUpdate(statement);
    }

    /*
     getInstance()

     @return Database: The database instance
     @throws SQLException
     */
    public static Database getInstance() throws SQLException{
        if ( instance == null ) {
            instance = new Database();
        }
        return instance;
    }

    // Creates a connection to the database
    private Connection connect() throws SQLException{
        String url = "jdbc:sqlite:src/main/resources/dnd_db.db";
        return DriverManager.getConnection(url);
    }

}