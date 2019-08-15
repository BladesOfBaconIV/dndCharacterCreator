import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {

    //Tries to make a connection to the database
    //Fails if no connection can be made
    @Test
    public void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/dnd_db.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
        } catch (Exception e) { }
        assertNotNull(conn);
    }

}
