package databaseHandlers;

import java.sql.SQLException;

// Interface of methods to be implemented by classes who's object are to be stored in the database
public interface Storable {

    void save() throws SQLException, IllegalAccessException;

    //TODO
//    Object load();
//    ArrayList<Object> loadAll();

}
