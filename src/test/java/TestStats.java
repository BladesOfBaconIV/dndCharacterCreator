import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStats {

    private static final Stats stats = new Stats(new int[] {2, 4, 6, 2, 1, 17});
    private static final Stats mods = new Stats(new int[] {1, 2, 12, 3, 10, 2});

    @Test
    void testAddModifiers() {
        Stats expected = new Stats(new int[] {3, 6, 18, 5, 11, 19});
        stats.addModifiers(mods);
        assertTrue(equals(stats, expected));
    }

    @Test
    void testGetStatByName() {
        assertEquals(12, mods.getStatByName(Stats.StatNames.CON));
    }

    private boolean equals(Stats s1, Stats s2) {
        for (int i = 0; i < 6; i++) {
            if (s1.getStats()[i] != s2.getStats()[i]) {
                return false;
            }
        }
        return true;
    }
}
