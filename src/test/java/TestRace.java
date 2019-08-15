import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

public class TestRace {

    //TODO Add test methods once more functionality added

    @Test
    public void save() {
        Exception e = null;
        try {
            Stats s = new Stats(1, 2, 3, 4, 5, 6);
            Race r = new Race("Test", s, new String[]{}, "");
            r.save();
        } catch (SQLException sqle) {
            e = sqle;
        }
        assertNotNull(e);
    }

}
