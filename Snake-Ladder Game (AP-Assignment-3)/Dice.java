import java.util.*;

public class Dice {
    private int sides;
    Dice(int sides) {
        this.sides=sides;
    }

    public void set_sides(int sides) {
        this.sides=sides;
    }
    public int get_sides() {
        return this.sides;
    }

    public int roll() {
        Random random=new Random();
        return random.nextInt(this.sides)+1;
    }
}
