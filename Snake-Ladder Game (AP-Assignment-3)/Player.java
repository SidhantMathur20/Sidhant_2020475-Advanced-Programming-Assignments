import java.util.*;

public class Player {
    private String name;
    private int position;
    private int points;
    Player(String name) {
        this.name=name;
        this.position=-1;
    }

    public String get_name() {
        return this.name;
    }

    public void set_position(int position) {
        this.position=position;
    }
    public int get_position() {
        return this.position;
    }

    public void set_points(int points) {
        this.points=points;
    }
    public int get_points() {
        return this.points;
    }

}
