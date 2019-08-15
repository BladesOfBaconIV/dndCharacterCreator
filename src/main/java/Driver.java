import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) {
        Stats s = new Stats(1, 2, 3, 4, 5, 6);
        Race r = new Race("Dwarf", s, new String[]{"hello", "world"}, "test");
        try {
            r.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
