import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRace {

    @Test
    public void save() {
        boolean noError = true;
        try {
            Stats s = new Stats(1, 2, 3, 4, 5, 6);
            Race r = new Race("Test", s, new String[]{}, "Lorem Ipsum");
            r.save();
        } catch (Exception e) {
            noError = false;
        }
        assertTrue(noError);
    }

}
