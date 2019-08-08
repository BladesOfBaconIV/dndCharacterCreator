import java.util.Random;

public class Dice {

    public static final Dice D20 = new Dice();
    public static final Dice D4 = new Dice(4);
    public static final Dice D6 = new Dice(6);
    public static final Dice D10 = new Dice(10);
    public static final Dice D100 = new Dice(100);

    private static Random roller = new Random();
    private int size;

    public Dice(int size) {
        this.size = size;
    }

    public Dice() {
        this.size = 20;
    }

    public int roll() {
        return roller.nextInt(this.size) + 1;
    }

    public int getSize() {
        return this.size;
    }

}
